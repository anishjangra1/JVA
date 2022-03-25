package com.jva.data.model

data class OrganiztionType(
    val data: List<Organiztion>,
    val message: String,
    val status: Int
)

data class Organiztion(
    val category_id: String,
    val category_title: String
)