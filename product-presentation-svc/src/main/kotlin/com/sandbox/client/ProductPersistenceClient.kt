package com.sandbox.client

import com.sandbox.client.ProductPersistenceClient.Companion.PRODUCT_PERSISTENCE_SERVICE_NAME
import com.sandbox.presentation.spec.product.Product
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = PRODUCT_PERSISTENCE_SERVICE_NAME)
interface ProductPersistenceClient {

    companion object {
        const val PRODUCT_PERSISTENCE_SERVICE_NAME = "product-persistence-service"
        const val PRODUCT_PERSISTENCE_ENDPOINT = "/products"
    }

    @GetMapping(PRODUCT_PERSISTENCE_ENDPOINT, consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getProducts(): Iterable<Product>

    @GetMapping("$PRODUCT_PERSISTENCE_ENDPOINT/{id}", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getProduct(@PathVariable("id") id: Long): Product
}