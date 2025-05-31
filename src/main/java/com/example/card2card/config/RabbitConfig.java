package com.example.card2card.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures RabbitMQ components: queue, exchange, binding, and JSON message converter.
 */
@Configuration
public class RabbitConfig {

    public static final String TRANSFER_QUEUE = "transfer-queue";
    public static final String TRANSFER_EXCHANGE = "transfer-exchange";
    public static final String TRANSFER_ROUTING_KEY = "transfer.key";

    @Bean
    public Queue transferQueue() {
        return new Queue(TRANSFER_QUEUE, true);
    }

    @Bean
    public TopicExchange transferExchange() {
        return new TopicExchange(TRANSFER_EXCHANGE);
    }

    @Bean
    public Binding transferBinding(Queue transferQueue, TopicExchange transferExchange) {
        return BindingBuilder.bind(transferQueue)
                .to(transferExchange)
                .with(TRANSFER_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}
