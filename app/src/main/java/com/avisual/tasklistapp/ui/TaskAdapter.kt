package com.avisual.tasklistapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.avisual.tasklistapp.databinding.ItemMainTaskBinding
import com.avisual.tasklistapp.model.Task

class TaskAdapter(var tasks: List<Task>) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemMainTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int = tasks.size

    class ViewHolder(var binding: ItemMainTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.titleTask.text = task.title
            binding.description.text = task.description
        }
    }

    fun setItems(task: List<Task>) {
        this.tasks = task
        notifyDataSetChanged()
    }

}