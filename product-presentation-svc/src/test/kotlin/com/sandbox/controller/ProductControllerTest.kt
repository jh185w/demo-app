package com.sandbox.controller

import com.sandbox.client.ProductPersistenceClient
import com.sandbox.util.TestDataUtil
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.hasSize
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    @MockBean
    lateinit var productPersistenceClient: ProductPersistenceClient

    @Test
    fun get() {

        val expectedProducts = TestDataUtil.getProducts()
        Mockito.`when`(productPersistenceClient.getProducts()).thenReturn(expectedProducts)

        mockMvc.perform(get(ProductController.PRODUCT_PRESENTATION_ENDPOINT))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("$", hasSize<Any>(2)))
    }

    @Test
    fun getById() {

        val productId = 1L
        val expectedProduct = TestDataUtil.getProducts().first()

        Mockito.`when`(productPersistenceClient.getProduct(productId)).thenReturn(expectedProduct)

        mockMvc.perform(get("${ProductController.PRODUCT_PRESENTATION_ENDPOINT}/{id}", productId))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(content().string(containsString(productId.toString())))
    }
}