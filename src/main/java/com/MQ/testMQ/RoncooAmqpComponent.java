package com.MQ.testMQ;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RoncooAmqpComponent {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private static final ObjectMapper MAPPER = new ObjectMapper();


    public void send(String msg) {
        this.amqpTemplate.convertAndSend("TESTMQ", msg);
    }

    @RabbitListener(queues = "fanout.A")
    public void receiveQueueA(String text) throws IOException {
        System.out.println("fanout.A接受到：" + text);
        JsonNode jsonNode = MAPPER.readTree(text);
        System.out.println("fanout.A接受到名字：" + jsonNode.get("name"));
        System.out.println("fanout.A接受到年龄：" + jsonNode.get("age"));
    }

    @RabbitListener(queues = "fanout.B")
    public void receiveQueueB(String text) {
        System.out.println("fanout.B接受到：" + text);
    }

    @RabbitListener(queues = "fanout.C")
    public void receiveQueueC(String text) throws IOException {
        JsonNode jsonNode = MAPPER.readTree(text);
        System.out.println("fanout.C接受到：" + jsonNode.get("name"));
    }

}