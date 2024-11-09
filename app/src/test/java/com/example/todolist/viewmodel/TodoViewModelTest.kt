package com.example.todolist.viewmodel

import android.app.Application
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.todolist.data.local.entities.ToDoTask
import org.junit.Assert.*
import org.junit.Test

class TodoViewModelTest{
    val context = ApplicationProvider.getApplicationContext<Application>()
    val todoViewModel = TodoViewModel(context)
    @Test
    fun insertTest(){
        val task = ToDoTask(0,"Task 1","Description 1")
        todoViewModel.insert(task)
//        assertEquals()
    }

}