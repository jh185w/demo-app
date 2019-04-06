package com.sandbox.autoconfigure.jms

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.annotation.EnableJms
import org.springframework.jms.config.DefaultJmsListenerContainerFactory
import org.springframework.jms.config.JmsListenerContainerFactory
import org.springframework.jms.listener.DefaultMessageListenerContainer
import org.springframework.jms.support.converter.MappingJackson2MessageConverter
import org.springframework.jms.support.converter.MessageConverter
import org.springframework.jms.support.converter.MessageType
import javax.jms.ConnectionFactory
import javax.jms.Session.CLIENT_ACKNOWLEDGE

@EnableJms
@Configuration
@ConditionalOnClass(ConnectionFactory::class)
class CustomJmsAutoConfiguration {

    companion object {
        const val JMS_CONTAINER_FACTORY = "myFactory"
    }

    @ConditionalOnMissingBean
    @Bean
    fun myFactory(connectionFactory: ConnectionFactory, configurer: DefaultJmsListenerContainerFactoryConfigurer): JmsListenerContainerFactory<DefaultMessageListenerContainer> {

        val factory = DefaultJmsListenerContainerFactory().also {
            it.setSessionAcknowledgeMode(CLIENT_ACKNOWLEDGE)
            it.setMessageConverter(jacksonJmsMessageConverter())
        }

        configurer.configure(factory, connectionFactory)
        return factory
    }

    @ConditionalOnMissingBean
    @Bean // Serialize message content to json using TextMessage
    fun jacksonJmsMessageConverter(): MessageConverter {

        return MappingJackson2MessageConverter().also {
            it.setTargetType(MessageType.TEXT)
            it.setTypeIdPropertyName("_type")
        }
    }
}