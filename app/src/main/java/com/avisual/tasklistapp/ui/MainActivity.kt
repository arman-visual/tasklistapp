package com.avisual.tasklistapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.avisual.tasklistapp.database.Db
import com.avisual.tasklistapp.databinding.ActivityMainBinding
import com.avisual.tasklistapp.repository.TaskRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var taskRepository: TaskRepository
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buildDependencies()
        viewModel = buildViewModel()
        configureRecycler()
        setFabListener()
        subscribeUi()
    }

    private fun buildDependencies() {
        val database = Db.getDatabase(this.applicationContext)
        taskRepository = TaskRepository(database)
    }

    private fun buildViewModel(): MainViewModel {
        val factory = MainViewModelFactory(taskRepository)
        return ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    private fun configureRecycler() {
        taskAdapter = TaskAdapter(emptyList())
        binding.recycler.adapter = taskAdapter
    }

    private fun setFabListener() {
        binding.fabAddtask.setOnClickListener {
            startActivity(Intent(this, RegisterTaskActivity::class.java))
        }
    }

    private fun subscribeUi() {
        viewModel.storedTask.observe(this, {
            taskAdapter.setItems(it)
        })
    }

    override fun onResume() {
        viewModel.refresh()
        super.onResume()
    }
}