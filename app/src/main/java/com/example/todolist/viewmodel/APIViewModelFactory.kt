package com.example.todolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.data.repository.APIRepository


class APIViewModelFactory(private val repository: APIRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(APIViewModel::class.java)) {
            return APIViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}