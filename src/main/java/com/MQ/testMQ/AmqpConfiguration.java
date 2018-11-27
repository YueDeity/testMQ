package com.MQ.testMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfiguration {
    /**
     * 创建一个消息队列
     * @return
     */
    @Bean
    public Queue AMessage() {
        return new Queue("fanout.A");
    }

    @Bean
    public Queue BMessage() {
        return new Queue("fanout.B");
    }

    @Bean
    public Queue CMessage() {
        return new Queue("fanout.C");
    }

    /**
     * 设置一个交换机
     * @return
     */
    @Bean
    public TopicExchange userTopicExchange() {
        TopicExchange topicExchange = new TopicExchange("topicExchange");
        return topicExchange;
    }

    /**
     * 把队列绑定到交换机
     * @return
     */
    @Bean
    Binding bindingExchangeA(Queue AMessage, TopicExchange topicExchange) {
        //to：交换机类型  with：匹配类型
        return BindingBuilder.bind(AMessage).to(topicExchange).with("item.*");
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, TopicExchange topicExchange) {
        return BindingBuilder.bind(BMessage).to(topicExchange).with("item.update");
    }

    @Bean
    Binding bindingExchangeC(Queue CMessage, TopicExchange topicExchange) {
        return BindingBuilder.bind(CMessage).to(topicExchange).with("item.delete");
    }

}