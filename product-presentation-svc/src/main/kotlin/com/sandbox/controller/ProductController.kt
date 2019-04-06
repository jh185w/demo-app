package com.sandbox.controller

import com.sandbox.controller.ProductController.Companion.PRODUCT_PRESENTATION_ENDPOINT
import com.sandbox.presentation.spec.product.Product
import com.sandbox.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(PRODUCT_PRESENTATION_ENDPOINT)
class ProductController(val productService: ProductService) {

    companion object {
        const val PRODUCT_PRESENTATION_ENDPOINT = "/products"
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun get(): ResponseEntity<Iterable<Product>> = ResponseEntity.status(HttpStatus.OK)
            .body(productService.getProducts())

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getById(@PathVariable("id") id: Long): ResponseEntity<Product> = ResponseEntity.status(HttpStatus.OK)
            .body(productService.getProduct(id))

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun addProduct(@RequestBody @Valid product: Product) {
        productService.addProduct(product)
    }
}