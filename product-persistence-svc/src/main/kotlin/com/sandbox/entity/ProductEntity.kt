package com.sandbox.entity

import javax.persistence.*
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name ="product")
data class ProductEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long?,

        @NotNull
        @Size(min = 1)
        val name: String,

        @NotNull
        @Size(min = 1)
        val description: String,

        @NotNull
        @DecimalMin(value = "0")
        val price: Double
)