package com.avisual.tasklistapp.ui.mainActivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.avisual.tasklistapp.database.Db
import com.avisual.tasklistapp.databinding.ActivityMainBinding
import com.avisual.tasklistapp.model.Task
import com.avisual.tasklistapp.repository.TaskRepository
import com.avisual.tasklistapp.ui.registerTask.RegisterTaskActivity
import kotlinx.coroutines.flow.collect

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
        setUpUi()
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

    private fun setUpUi() {
        binding.fabAddtask.setOnClickListener {
            startActivity(Intent(this, RegisterTaskActivity::class.java))
        }
        taskAdapter = TaskAdapter(emptyList(), ::onClickDeleteButton)
        binding.recycler.adapter = taskAdapter
    }

    private fun onClickDeleteButton(task: Task) {
        viewModel.deleteTask(task)
        Toast.makeText(this, "Delete Task ${task.title}", Toast.LENGTH_SHORT).show()
    }

    private fun subscribeUi() {
        lifecycleScope.launchWhenStarted {
            viewModel.storedTask.collect { taskAdapter.setItems(it) }
        }
    }
}