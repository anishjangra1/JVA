package com.jva.data.model

data class SelfHelpAllCategory(
    val data: List<AllCategory>,
    val message: String,
    val status: Int
)

data class AllCategory(
    val link: String,
    val other_sub_category: String
)