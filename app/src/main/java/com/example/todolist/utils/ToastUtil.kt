package com.example.todolist.utils

import android.content.Context
import android.widget.Toast

class ToastUtil {
    fun toast(msg: String,context: Context){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
    }
}