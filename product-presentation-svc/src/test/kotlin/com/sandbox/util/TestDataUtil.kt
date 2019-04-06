package com.sandbox.util

import com.sandbox.presentation.spec.product.Product

class TestDataUtil {

    companion object {
        fun getProducts(): Iterable<Product> {
            return listOf(
                    Product()
                            .withId(1L)
                            .withName("Coat")
                            .withDescription("Heavy double-breasted coat made of wool fabric")
                            .withPrice(50.00),
                    Product()
                            .withId(2L)
                            .withName("Jeans")
                            .withDescription("Slim-fit distressed denim")
                            .withPrice(25.00)
            )
        }
    }
}