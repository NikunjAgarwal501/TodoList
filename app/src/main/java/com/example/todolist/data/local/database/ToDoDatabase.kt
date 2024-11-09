package com.example.todolist.data.local.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todolist.data.local.dao.ToDoDao
import com.example.todolist.data.local.entities.ToDoTask

@Database(entities = [ToDoTask::class], version = 2, exportSchema = true, autoMigrations =[
    AutoMigration(
        from = 1, to = 2
    )
])
abstract class ToDoDatabase: RoomDatabase() {
    abstract fun todoDao() : ToDoDao
    companion object{
        val MIGRATION_1_2 = object : Migration(1,2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE todo_table ADD COLUMN priority TEXT NOT NULL DEFAULT high")
            }
        }
        private const val DB_NAME = "todo_database"
        @Volatile
        private var INSTANCE: ToDoDatabase? = null
        fun getDatabase(context: Context): ToDoDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDatabase::class.java,
                    DB_NAME
                ).addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}