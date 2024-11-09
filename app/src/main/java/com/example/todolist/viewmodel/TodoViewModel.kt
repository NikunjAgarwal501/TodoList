package com.example.todolist.viewmodel

import android.app.Application
import androidx.compose.runtime.saveable.listSaver
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.local.database.ToDoDatabase
import com.example.todolist.data.local.entities.ToDoTask
import com.example.todolist.data.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application): AndroidViewModel(application) {
    private val todoDao = ToDoDatabase.getDatabase(application).todoDao()
    private val repository: ToDoRepository = ToDoRepository(todoDao)
    private val _task = MutableLiveData<ToDoTask>()
    val task: LiveData<ToDoTask> get() = _task
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading
    private val allTask : LiveData<List<ToDoTask>>
    init{
        _loading.value = true
        allTask = repository.getAllTasks().also {
            _loading.value = false
        }
    }
    fun getAllTasks(): LiveData<List<ToDoTask>> {
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            allTask
            _loading.postValue(false)
        }
        return allTask
    }
    fun insert(toDoTask: ToDoTask){
        if (!isTaskValid(toDoTask)) return
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertTask(toDoTask)
        }
    }
    private fun isTaskValid(task: ToDoTask): Boolean {
        return task.title.isNotBlank()
    }

    fun delete(toDoTask: ToDoTask){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteTask(toDoTask)
        }
    }

    fun update(toDoTask: ToDoTask){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTask(toDoTask)
        }
    }

    fun getTaskById(id:Int): LiveData<ToDoTask> {
            return repository.getTaskById(id)

    }
}