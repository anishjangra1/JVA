package com.jva.data.api
import com.jva.data.model.User
import retrofit2.http.GET

interface ApiService {

    @GET("/users")
    suspend fun getUsers(): List<User>

}