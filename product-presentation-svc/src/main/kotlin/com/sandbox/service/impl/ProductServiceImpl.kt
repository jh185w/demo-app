package com.sandbox.service.impl

import com.sandbox.client.ProductPersistenceClient
import com.sandbox.client.ProductPersistenceJmsClient
import com.sandbox.presentation.spec.product.Product
import com.sandbox.service.ProductService
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(val productPersistenceClient: ProductPersistenceClient, val productPersistenceJmsClient: ProductPersistenceJmsClient) : ProductService {

    override fun getProducts(): Iterable<Product> = productPersistenceClient.getProducts()
    override fun getProduct(id: Long): Product = productPersistenceClient.getProduct(id)
    override fun addProduct(product: Product) = productPersistenceJmsClient.addProduct(product)
}