package com.example.todolist.data.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_2_3 = object : Migration (2,3){
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("Alter table todo_table rename column age to current_age")
    }


}