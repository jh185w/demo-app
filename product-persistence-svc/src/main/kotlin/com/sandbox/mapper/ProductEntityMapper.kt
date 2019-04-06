package com.sandbox.mapper

import com.sandbox.entity.ProductEntity
import com.sandbox.presentation.spec.product.Product

class ProductEntityMapper {

    companion object {

        val mapToProduct = { input: ProductEntity ->
            Product()
                    .withId(input.id)
                    .withName(input.name)
                    .withDescription(input.description)
                    .withPrice(input.price)
        }

        val mapToProductEntity = { input: Product ->
            ProductEntity(
                    input.id,
                    input.name,
                    input.description,
                    input.price)

        }

    }
}