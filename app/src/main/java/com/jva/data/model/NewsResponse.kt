package com.jva.data.model

data class NewsResponse(
    val data: List<NewsData>,
    val message: String,
    val status: String
)


data class NewsData(
    val added_date: String,
    val attachment: Any,
    val details: String,
    val link: String,
    val srno: String,
    val title: String,
    val user_id: String
)