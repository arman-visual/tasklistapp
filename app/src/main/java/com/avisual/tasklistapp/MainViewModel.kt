package com.avisual.tasklistapp

import com.avisual.tasklistapp.common.ScopeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ScopeViewModel() {

    fun saveTask() {
        launch(Dispatchers.IO) {
            TODO()
        }
    }
}