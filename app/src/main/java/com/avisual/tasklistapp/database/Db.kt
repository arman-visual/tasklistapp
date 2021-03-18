package com.avisual.tasklistapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.avisual.tasklistapp.model.Task


@Database(entities = [Task::class], version = 1)
abstract class Db : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        private var INSTANCE: Db? = null
        private const val DATABASE_NAME = "task-app.db"

        fun getDatabase(context: Context): Db {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext, Db::class.java, DATABASE_NAME
                ).build()
            }
            return INSTANCE!!
        }
    }
}
