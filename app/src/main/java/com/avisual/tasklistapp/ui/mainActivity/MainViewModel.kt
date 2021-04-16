package com.avisual.tasklistapp.ui.mainActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.avisual.tasklistapp.common.ScopeViewModel
import com.avisual.tasklistapp.model.Task
import com.avisual.tasklistapp.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val taskRepository: TaskRepository) : ScopeViewModel() {

    val storedTask: Flow<List<Task>> get() = taskRepository.getAllTasks()

}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val taskRepository: TaskRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MainViewModel(taskRepository) as T
}
