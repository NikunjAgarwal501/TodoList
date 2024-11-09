package com.example.todolist.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class ToDoTask (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: String
)