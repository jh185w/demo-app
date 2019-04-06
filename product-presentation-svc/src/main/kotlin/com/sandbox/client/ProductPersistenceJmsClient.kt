package com.sandbox.client

import com.sandbox.presentation.spec.product.Product
import org.slf4j.LoggerFactory
import org.springframework.jms.core.JmsTemplate
import org.springframework.stereotype.Component

@Component
class ProductPersistenceJmsClient(val jmsTemplate: JmsTemplate) {

    companion object {
        private val LOG = LoggerFactory.getLogger(ProductPersistenceJmsClient::class.java)
        private const val ADD_PRODUCT_QUEUE_NAME = "com.sandbox.jms.queue.product.add"
    }

    fun addProduct(product: Product) = jmsTemplate.convertAndSend(ADD_PRODUCT_QUEUE_NAME, product)
            .also { LOG.info("Product enqueued at [$ADD_PRODUCT_QUEUE_NAME]; Product: $product") }
}