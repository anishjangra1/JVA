package com.jva.data.model

data class ServiceResponse(
    val data: List<ServiceData>,
    val message: String,
    val status: String
)


data class ServiceData(
    val product_title: String,
    val images: String
)
