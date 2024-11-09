package com.example.todolist

import com.example.todolist.data.api_service.APIService
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    lateinit var apiService: APIService
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Before
    fun makeApi(){
        apiService = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }
    @Test
    fun apiTesting() = runBlocking{
        val response = apiService.getUsers()
        assertEquals(response.isSuccessful,true)
    }
    @Test
    fun apiBodyTesting() = runBlocking {
        val response = apiService.getUsers().body()
        assertEquals(response?.get(0)?.login,"mojombo")
    }
    @Test
    fun apiResponseLengthTesting() = runBlocking {
        val response = apiService.getUsers()
        assertEquals(response.body()?.size, 30)
    }
}