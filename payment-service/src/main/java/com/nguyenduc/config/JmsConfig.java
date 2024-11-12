package com.nguyenduc.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSslConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@EnableJms
@Configuration
public class JmsConfig {
    @Value("${spring.activemq.broker-url:''}")
    private String BROKER_URL;
    @Value("${spring.activemq.user:''}")
    private String BROKER_USERNAME;
    @Value("${spring.activemq.password:''}")
    private String BROKER_PASSWORD;

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQSslConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(BROKER_URL);
        activeMQConnectionFactory.setUserName(BROKER_USERNAME);
        activeMQConnectionFactory.setPassword(BROKER_PASSWORD);

        return activeMQConnectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());

        return jmsTemplate;
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrency("1-1");
        return factory;
    }
}
