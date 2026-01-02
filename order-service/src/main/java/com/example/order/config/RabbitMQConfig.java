package com.example.order.config;

import com.example.order.ApplicationProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

@Configuration
public class RabbitMQConfig {

    private final ApplicationProperties applicationProperties;

    public RabbitMQConfig(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(applicationProperties.orderEventsExchange());
    }

    @Bean
    Queue newOrderQueue() {
        return QueueBuilder.durable(applicationProperties.newOrdersQueue()).build();
    }

    @Bean
    Binding newOrderQueueBinding() {
        return BindingBuilder.bind(newOrderQueue()).to(exchange()).with(applicationProperties.newOrdersQueue());
    }


    @Bean
    Queue deliveredOrderQueue() {
        return QueueBuilder.durable(applicationProperties.deliveredOrdersQueue()).build();
    }

    @Bean
    Binding deliveredOrderQueueBinding() {
        return BindingBuilder.bind(deliveredOrderQueue()).to(exchange()).with(applicationProperties.deliveredOrdersQueue());
    }


    @Bean
    Queue cancelledOrdersQueue() {
        return QueueBuilder.durable(applicationProperties.cancelledOrdersQueue()).build();
    }

    @Bean
    Binding cancelledOrdersQueueBinding() {
        return BindingBuilder.bind(cancelledOrdersQueue()).to(exchange()).with(applicationProperties.cancelledOrdersQueue());
    }

    @Bean
    Queue errorOrdersQueue() {
        return QueueBuilder.durable(applicationProperties.errorOrdersQueue()).build();
    }

    @Bean
    Binding errorOrdersQueueBinding() {
        return BindingBuilder.bind(errorOrdersQueue()).to(exchange()).with(applicationProperties.errorOrdersQueue());
    }


    @Bean
    public Jackson2JsonMessageConverter jacksonConverter(ObjectMapper mapper) {
        return new Jackson2JsonMessageConverter(mapper);
    }


    @Bean
    public RabbitTemplate  rabbitTemplate(ConnectionFactory connectionFactory , ObjectMapper mapper  ) {
        final var rabbitTemplate = new RabbitTemplate( connectionFactory);
        rabbitTemplate.setMessageConverter(jacksonConverter(mapper));
        return rabbitTemplate;
    }

}
