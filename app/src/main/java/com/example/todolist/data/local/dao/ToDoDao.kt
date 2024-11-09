package com.example.todolist.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todolist.data.local.entities.ToDoTask

@Dao
interface ToDoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(toDoTask: ToDoTask)

    @Update
    suspend fun updateTask(toDoTask: ToDoTask)

    @Delete
    suspend fun deleteTask(toDoTask: ToDoTask)

    @Query("Select * from todo_table order by id")
    fun getAllTask(): LiveData<List<ToDoTask>>

    @Query("Select * from todo_table where id = :id")
    fun getTaskById(id: Int): LiveData<ToDoTask>

}