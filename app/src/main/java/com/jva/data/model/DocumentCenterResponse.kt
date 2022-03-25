package com.jva.data.model

data class DocumentCenterResponse(
    val data: List<DocumentCenter>,
    val message: String,
    val status: String
)
data class DocumentCenter(
    val document: String,
    val remarks: Any,
    val services: String
)