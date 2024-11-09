package com.example.todolist.network

import com.example.todolist.data.api_service.APIService
import com.example.todolist.data.local.entities.APIResponseModelItem
import retrofit2.Response

class NetworkClient {
    private var  retrofit = RetrofitClient.getRetrofit()
    fun getUsers(): APIService{
        return retrofit.create(APIService::class.java)
    }
}