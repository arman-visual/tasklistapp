package com.avisual.tasklistapp.common

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.avisual.tasklistapp.common.Scope

abstract class ScopeViewModel : ViewModel(), Scope by Scope.Impl() {

    init {
        initScope()
    }

    @CallSuper
    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }

}