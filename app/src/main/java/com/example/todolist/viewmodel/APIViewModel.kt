package com.example.todolist.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.local.entities.APIResponseModelItem
import com.example.todolist.data.repository.APIRepository
import kotlinx.coroutines.launch


class APIViewModel(private val repository: APIRepository): ViewModel() {
    private val _users = MutableLiveData<List<APIResponseModelItem>?>()
    val users: LiveData<List<APIResponseModelItem>?> get() = _users
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun fetchUsers(context:Context) {
        _loading.value = true
        viewModelScope.launch {
            val usersData = repository.getUsers(context)
            _loading.value = false
            _users.postValue(usersData)
        }
    }
}
