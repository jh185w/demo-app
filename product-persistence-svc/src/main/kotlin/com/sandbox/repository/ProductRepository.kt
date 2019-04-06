package com.sandbox.repository

import com.sandbox.entity.ProductEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: CrudRepository<ProductEntity, Long>