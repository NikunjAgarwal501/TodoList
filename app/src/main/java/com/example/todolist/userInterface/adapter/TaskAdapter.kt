package com.example.todolist.userInterface.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.local.entities.ToDoTask
import com.example.todolist.databinding.RowLayoutBinding


class TaskAdapter(private val deleteAction: (ToDoTask) -> Unit,private val updateAction: (ToDoTask) -> Unit) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private var taskList: List<ToDoTask> = emptyList()

    inner class ViewHolder(val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(toDoTask: ToDoTask){
            binding.todoEntry = toDoTask
            binding.executePendingBindings()
            binding.btnDelete.setOnClickListener {
                deleteAction(toDoTask)
            }
            binding.btnUpdate.setOnClickListener {
                updateAction(toDoTask)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = taskList[position]
        holder.bind(task)
        }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun updateTasks(newTasks: List<ToDoTask>) {
        taskList = newTasks
        notifyDataSetChanged()
    }
    fun setTasks(tasks: List<ToDoTask>) {
        this.taskList = tasks
        notifyDataSetChanged()
    }
}
