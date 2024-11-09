package com.example.todolist.data.api_service

import com.example.todolist.data.local.entities.APIResponseModelItem
import retrofit2.Response
import retrofit2.http.GET
interface APIService {
    @GET("users")
    suspend fun getUsers(): Response<List<APIResponseModelItem>>
}
