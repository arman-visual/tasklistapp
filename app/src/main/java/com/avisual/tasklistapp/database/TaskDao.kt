package com.avisual.tasklistapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.avisual.tasklistapp.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM Task")
    fun getAll(): List<Task>

    @Query("SELECT * FROM Task")
    fun getAllLiveData(): LiveData<List<Task>>

    @Query("SELECT * FROM Task")
    fun getAllWithFlow(): Flow<List<Task>>

    @Query("SELECT COUNT(id) FROM Task")
    fun taskCount(): Int

    @Insert(onConflict = IGNORE)
    fun insert(task: Task)

    @Update
    fun updateTask(task: Task)

    @Delete
    fun delete(task: Task)

}