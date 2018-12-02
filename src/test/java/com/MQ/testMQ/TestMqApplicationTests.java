package com.MQ.testMQ;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMqApplicationTests {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Autowired
	private RabbitTemplate template;

	@Test
	public void send() {
		User us = new User();
		us.setName("张三");
		us.setAge("18");
		/*String context = "item.update";*/
		System.out.println("Sender : " + us);
		this.template.convertAndSend("topicExchange","item.add", toJsonStr(us));
	}

	public static String toJsonStr(Object obj){
		try {
			return MAPPER.writeValueAsString(obj);
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
