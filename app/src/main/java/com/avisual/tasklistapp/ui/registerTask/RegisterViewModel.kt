package com.avisual.tasklistapp.ui.registerTask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.avisual.tasklistapp.common.ScopeViewModel
import com.avisual.tasklistapp.model.Task
import com.avisual.tasklistapp.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(private val taskRepository: TaskRepository) : ScopeViewModel() {

    fun saveTask(task: Task) {
        launch(Dispatchers.IO) {
            taskRepository.save(task)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class RegisterViewModelFactory(private val taskRepository: TaskRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        RegisterViewModel(taskRepository) as T
}
