package com.sandbox.controller

import com.sandbox.controller.ProductPersistenceController.Companion.PRODUCT_PRESENTATION_ENDPOINT
import com.sandbox.presentation.spec.product.Product
import com.sandbox.service.ProductPersistenceService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(PRODUCT_PRESENTATION_ENDPOINT)
class ProductPersistenceController(val productPersistenceService: ProductPersistenceService) {

    companion object {
        const val PRODUCT_PRESENTATION_ENDPOINT = "/products"
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun get(): ResponseEntity<Iterable<Product>> = ResponseEntity.status(HttpStatus.OK)
            .body(productPersistenceService.getProducts())

    @GetMapping(value = "/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getById(@PathVariable("id") id: Long) = ResponseEntity.status(HttpStatus.OK)
            .body(productPersistenceService.getProduct(id))

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun post(@RequestBody @Valid product: Product): ResponseEntity<Product> = ResponseEntity.status(HttpStatus.CREATED)
            .body(productPersistenceService.addProduct(product))

}