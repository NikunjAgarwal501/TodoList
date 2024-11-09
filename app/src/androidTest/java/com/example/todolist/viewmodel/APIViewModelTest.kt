package com.example.todolist.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.todolist.data.repository.APIRepository
import com.example.todolist.data.repository.ToDoRepository
import com.example.todolist.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class APIViewModelTest {
    private val testDispatcher = StandardTestDispatcher()
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    lateinit var repository: APIRepository
    @Mock
    lateinit var application: Application
    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
    }
    @Test
    fun getUsersTesting() = runTest{
//        Mockito.when(repository.getUsers(application)).thenReturn(NetworkResult.Success(emptyList()))

        val sut = APIViewModel(repository)
        sut.fetchUsers(application)
        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.users.getOrAwaitValue()
        assertEquals(0,result?.size)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}