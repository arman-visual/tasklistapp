package com.avisual.tasklistapp.ui.mainActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.avisual.tasklistapp.common.ScopeViewModel
import com.avisual.tasklistapp.model.Task
import com.avisual.tasklistapp.repository.TaskRepository
import kotlinx.coroutines.launch

class MainViewModel(private val taskRepository: TaskRepository) : ScopeViewModel() {

    val storedTask: LiveData<List<Task>> get() = taskRepository.getAllTasks().asLiveData()

    fun deleteTask(task: Task) {
        launch {
            taskRepository.delete(task)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val taskRepository: TaskRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MainViewModel(taskRepository) as T
}
