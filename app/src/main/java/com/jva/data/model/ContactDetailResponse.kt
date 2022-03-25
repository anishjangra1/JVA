package com.jva.data.model

data class ContactDetailResponse(
    val data: List<ContactData>,
    val message: String,
    val status: String
)

data class ContactData(
    val added_date: String,
    val address: String,
    val description: String,
    val email: String,
    val image: Any,
    val mobile: String,
    val modified_date: String,
    val page_name: String,
    val srno: String,
    val status: String,
    val title: String
)