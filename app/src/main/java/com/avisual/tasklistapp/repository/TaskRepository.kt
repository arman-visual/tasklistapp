package com.avisual.tasklistapp.repository

import androidx.lifecycle.LiveData
import com.avisual.tasklistapp.database.Db
import com.avisual.tasklistapp.database.TaskDao
import com.avisual.tasklistapp.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskRepository(database: Db) {

    private var photoGalleryDao: TaskDao = database.taskDao()

    suspend fun save(task: Task) = withContext(Dispatchers.IO) {
        photoGalleryDao.insert(task)
    }

    suspend fun delete(task: Task) = withContext(Dispatchers.IO) {
        photoGalleryDao.delete(task)
    }

    suspend fun update(task: Task) = withContext(Dispatchers.IO) {
        photoGalleryDao.updateTask(task)
    }

    fun getAllLiveData() : LiveData<List<Task>> {
        return photoGalleryDao.getAllLiveData()
    }

    suspend fun getAll() = withContext(Dispatchers.IO) {
        photoGalleryDao.getAll()
    }

}