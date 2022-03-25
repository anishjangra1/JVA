package com.jva.data.model

data class SelfHelpSubCategoryResponse(
    val data: List<SelfHelpSubCategory>,
    val message: String,
    val status: Int
)

data class SelfHelpSubCategory(
    val added_date: String,
    val category_id: String,
    val srno: String,
    val status: String,
    val sub_category_id: String,
    val sub_name: String
)