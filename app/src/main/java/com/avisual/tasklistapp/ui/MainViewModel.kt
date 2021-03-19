package com.avisual.tasklistapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.avisual.tasklistapp.common.ScopeViewModel
import com.avisual.tasklistapp.model.Task
import com.avisual.tasklistapp.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val taskRepository: TaskRepository) : ScopeViewModel() {

    private val _storedTask = MutableLiveData<List<Task>>()
    val storedTask: LiveData<List<Task>> get() = _storedTask
    //val storedTask: LiveData<List<Task>> get() = taskRepository.getAllLiveData()

    init {
        refresh()
    }

    fun refresh() {
        launch(Dispatchers.IO) {
            _storedTask.postValue(taskRepository.getAll())
        }
    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val taskRepository: TaskRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MainViewModel(taskRepository) as T
}
