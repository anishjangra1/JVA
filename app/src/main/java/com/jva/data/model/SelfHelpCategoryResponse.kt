package com.jva.data.model

data class SelfHelpCategoryResponse(
    val data: List<SelfHelpCategory>,
    val message: String,
    val status: Int
)


data class SelfHelpCategory(
    val added_date: String,
    val category_id: String,
    val category_name: String,
    val module: String,
    val srno: String,
    val status: String
)