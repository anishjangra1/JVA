package com.jva.data.repository

import com.jva.data.api.ApiHelper


class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()
}