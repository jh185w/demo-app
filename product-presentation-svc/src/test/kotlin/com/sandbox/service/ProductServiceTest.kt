package com.sandbox.service

import com.sandbox.client.ProductPersistenceClient
import com.sandbox.util.TestDataUtil
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class ProductServiceTest {

    @Autowired
    lateinit var productService: ProductService

    @Autowired
    @MockBean
    lateinit var productPersistenceClient: ProductPersistenceClient

    @Test
    fun getProducts() {

        val expectedProducts = TestDataUtil.getProducts()
        Mockito.`when`(productPersistenceClient.getProducts()).thenReturn(expectedProducts)
        val actualProducts = productService.getProducts()

        println("Expected response: $expectedProducts")
        println("Actual response: $actualProducts")

        Assert.assertEquals(expectedProducts.count(), actualProducts.count())
    }

    @Test
    fun getProduct() {

        val productId = 1L
        val expectedProduct = TestDataUtil.getProducts().first()

        Mockito.`when`(productPersistenceClient.getProduct(productId)).thenReturn(expectedProduct)
        val actualProduct = productService.getProduct(productId)

        println("Expected response: $expectedProduct")
        println("Actual response: $actualProduct")

        Assert.assertEquals(actualProduct, expectedProduct)
    }

}