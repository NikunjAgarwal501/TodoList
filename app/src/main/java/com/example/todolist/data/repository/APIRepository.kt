package com.example.todolist.data.repository

import android.content.Context
import com.example.todolist.data.api_service.APIService
//import com.example.todolist.data.local.entities.APIResponseModel
import com.example.todolist.data.local.entities.APIResponseModelItem
import com.example.todolist.network.NetworkClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response


class APIRepository(private val networkClient: NetworkClient) {

    suspend fun getUsers(context: Context): List<APIResponseModelItem>? {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<List<APIResponseModelItem>> = networkClient.getUsers().getUsers()
                if (response.isSuccessful) {
                    response.body()
                } else {
                    null
                }
            }catch (e: Exception) {
                null
            }
        }
    }
}
