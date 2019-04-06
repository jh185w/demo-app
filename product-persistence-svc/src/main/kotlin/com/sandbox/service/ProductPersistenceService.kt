package com.sandbox.service

import com.sandbox.presentation.spec.product.Product

interface ProductPersistenceService {

    fun getProducts(): Iterable<Product>
    fun getProduct(id: Long): Product

    fun addProduct(product: Product): Product
}

