package com.sandbox.jms

import com.sandbox.autoconfigure.jms.CustomJmsAutoConfiguration.Companion.JMS_CONTAINER_FACTORY
import com.sandbox.presentation.spec.product.Product
import com.sandbox.service.ProductPersistenceService
import org.slf4j.LoggerFactory
import org.springframework.jms.annotation.JmsListener
import org.springframework.messaging.Message
import org.springframework.stereotype.Component

@Component
class MessageListener(val productPersistenceService: ProductPersistenceService) {

    companion object {
        private val LOG = LoggerFactory.getLogger(MessageListener::class.java)
        private const val ADD_PRODUCT_QUEUE = "com.sandbox.jms.queue.product.add"
    }

    @JmsListener(destination = ADD_PRODUCT_QUEUE, containerFactory = JMS_CONTAINER_FACTORY)
    fun recieveAddProductMessage(message: Message<Product>) {
        LOG.info("Received message from destination: [$ADD_PRODUCT_QUEUE}] Message: ${message.payload}")
        productPersistenceService.addProduct(message.payload)
    }
}