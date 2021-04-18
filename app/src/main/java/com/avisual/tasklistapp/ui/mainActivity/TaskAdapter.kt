package com.avisual.tasklistapp.ui.mainActivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.avisual.tasklistapp.R
import com.avisual.tasklistapp.databinding.ItemMainTaskBinding
import com.avisual.tasklistapp.model.Task

class TaskAdapter(
    var tasks: List<Task>,
    var onClickDeleteButton: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

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
        holder.buttonDelete.setOnClickListener { onClickDeleteButton(tasks[position]) }
    }

    override fun getItemCount(): Int = tasks.size

    class ViewHolder(var binding: ItemMainTaskBinding) : RecyclerView.ViewHolder(binding.root) {

        var buttonDelete = binding.btDelete

        fun bind(task: Task) {
            binding.titleTask.text = task.title
            binding.description.text = task.description
            when (task.imageTask) {
                "Sport" -> binding.imageTask.setImageResource(R.drawable.ic_sport)
                "Housework" -> binding.imageTask.setImageResource(R.drawable.ic_housework)
                "Study" -> binding.imageTask.setImageResource(R.drawable.ic_study)
                else -> binding.imageTask.setImageResource(R.drawable.ic_launcher_background)
            }
            binding.btDelete
        }
    }

    fun setItems(task: List<Task>) {
        this.tasks = task
        notifyDataSetChanged()
    }

}