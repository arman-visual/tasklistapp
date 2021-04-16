package com.avisual.tasklistapp.ui.registerTask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.avisual.tasklistapp.database.Db
import com.avisual.tasklistapp.databinding.ActivityRegisterTaskBinding
import com.avisual.tasklistapp.model.Task
import com.avisual.tasklistapp.repository.TaskRepository

class RegisterTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterTaskBinding
    private lateinit var taskRepository: TaskRepository
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buildDependencies()
        viewModel = buildViewModel()
        binding = ActivityRegisterTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonSave.setOnClickListener { saveTask() }
    }

    private fun buildDependencies() {
        val database = Db.getDatabase(this.applicationContext)
        taskRepository = TaskRepository(database)
    }

    private fun buildViewModel(): RegisterViewModel {
        val factory = RegisterViewModelFactory(taskRepository)
        return ViewModelProvider(this, factory).get(RegisterViewModel::class.java)
    }

    private fun saveTask() {
        val title = binding.etTitle.text.toString()
        val description = binding.etDescription.text.toString()
        val task = Task(0, title, description)
        viewModel.saveTask(task)
        finish()
    }

}