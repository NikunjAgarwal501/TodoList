package com.example.todolist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.viewModels
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.todolist.data.local.dao.ToDoDao
import com.example.todolist.data.local.database.ToDoDatabase
import com.example.todolist.data.local.entities.ToDoTask
import com.example.todolist.viewmodel.TodoViewModel
import kotlinx.coroutines.runBlocking
import org.junit.After
import androidx.fragment.app.viewModels

import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TodoDaoTest {
    @get:Rule
    val instantTaskExecutorRule= InstantTaskExecutorRule()
    lateinit var db : ToDoDatabase
    lateinit var dao: ToDoDao


    @Before
    fun setup(){
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ToDoDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = db.todoDao()
    }

    @Test
    fun insertData() = runBlocking{
        val task = ToDoTask(0,"Task 1","Description 1")
        dao.insertTask(task)

        val result = dao.getAllTask().getOrAwaitValue()

        Assert.assertEquals(1,result.size)
        Assert.assertEquals("Task 1", result[0].title)
    }
    @Test
    fun insertEmptyData() = runBlocking{
        val task = ToDoTask(1,"","Description 1")
        dao.insertTask(task)

        val result = dao.getAllTask().getOrAwaitValue()

        Assert.assertEquals(0,result.size)
//        Assert.assertEquals("Task 1", result[0].title)
    }

    @Test
    fun getTaskById() = runBlocking{
        val task = ToDoTask(0,"Task 1","Description 1")
        dao.insertTask(task)
        val result = dao.getTaskById(0).getOrAwaitValue()
        Assert.assertEquals("Task 1",result)
    }
    @Test
    fun getTasks() = runBlocking{
        val task = ToDoTask(0,"Task 1","Description 1")
        dao.insertTask(task)
        val result = dao.getAllTask().getOrAwaitValue()
        Assert.assertEquals(1,result.size)
    }


//    @Test
//    fun deleteData() = runBlocking {
//        val task = ToDoTask(0,"Task 1","Description 1")
//        dao.insertTask(task)
//    }

//    @After
//    fun tearDown(){
//        db.close()
//    }
}