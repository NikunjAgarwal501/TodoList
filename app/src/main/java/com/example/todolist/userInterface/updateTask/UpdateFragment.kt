package com.example.todolist.userInterface.updateTask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todolist.R
import com.example.todolist.data.local.entities.ToDoTask
import com.example.todolist.databinding.FragmentUpdateBinding
import com.example.todolist.utils.ToastUtil
import com.example.todolist.viewmodel.TodoViewModel

class UpdateFragment : Fragment() {

    private val viewModel: TodoViewModel by viewModels()
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val updateBinding = FragmentUpdateBinding.inflate(inflater, container, false)

        val id = args.taskId
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.task_priority,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        updateBinding.spinnerPriority.adapter = adapter

        viewModel.getTaskById(id).observe(viewLifecycleOwner) { task ->
            task?.let {
                updateBinding.etTaskName.setText(it.title)
                updateBinding.etTaskDesc.setText(it.description)
                val position = updateBinding.spinnerPriority.selectedItemPosition
                updateBinding.spinnerPriority.setSelection(position)
            }
        }
        var priority = ""
        updateBinding.spinnerPriority.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                priority = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        updateBinding.btnUpdate.setOnClickListener {

            val updatedTask = ToDoTask(
                id = id,
                title = updateBinding.etTaskName.text.toString(),
                description = updateBinding.etTaskDesc.text.toString(),
                priority = priority

            )
            viewModel.update(updatedTask)
            val msg = getString(R.string.task_updated_successfully)
            ToastUtil().toast(msg,requireContext())
            findNavController().navigateUp() // Navigate back
        }

        return updateBinding.root
    }
}
