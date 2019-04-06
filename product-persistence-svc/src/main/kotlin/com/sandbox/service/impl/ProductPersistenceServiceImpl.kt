package com.sandbox.service.impl

import com.sandbox.exception.ApiError
import com.sandbox.exception.ApiException
import com.sandbox.mapper.ProductEntityMapper
import com.sandbox.presentation.spec.product.Product
import com.sandbox.repository.ProductRepository
import com.sandbox.service.ProductPersistenceService
import org.springframework.stereotype.Service

@Service
class ProductPersistenceServiceImpl(val productRepository: ProductRepository) : ProductPersistenceService {

    override fun getProducts() = productRepository.findAll()
            .map { ProductEntityMapper.mapToProduct(it) }

    override fun getProduct(id: Long): Product {
        return productRepository.findById(id)
                .map { ProductEntityMapper.mapToProduct(it) }
                .orElseThrow { ApiException(ApiError.NOT_FOUND) }
    }

    override fun addProduct(product: Product): Product = productRepository
            .save(ProductEntityMapper.mapToProductEntity(product))
            .let { ProductEntityMapper.mapToProduct(it) }

}