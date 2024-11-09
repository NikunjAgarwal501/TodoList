package com.example.todolist.data.repository

import androidx.lifecycle.LiveData
import com.example.todolist.data.local.dao.ToDoDao
import com.example.todolist.data.local.entities.ToDoTask

class ToDoRepository(val toDoDao: ToDoDao) {

    fun getAllTasks(): LiveData<List<ToDoTask>>{
        return toDoDao.getAllTask()
    }
    suspend fun insertTask(toDoTask: ToDoTask){
        toDoDao.insertTask(toDoTask)
    }
    suspend fun updateTask(toDoTask: ToDoTask){
        toDoDao.updateTask(toDoTask)
    }
    suspend fun deleteTask(toDoTask: ToDoTask){
        toDoDao.deleteTask(toDoTask)
    }

    fun getTaskById(id : Int): LiveData<ToDoTask>{
        return toDoDao.getTaskById(id)
    }

}