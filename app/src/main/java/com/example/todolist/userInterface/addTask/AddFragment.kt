package com.example.todolist.userInterface.addTask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.data.local.entities.ToDoTask
import com.example.todolist.databinding.FragmentAddBinding
import com.example.todolist.utils.ToastUtil
import com.example.todolist.viewmodel.TodoViewModel

class AddFragment : Fragment() {

    private val viewModel: TodoViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentAddBinding.inflate(inflater)
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.task_priority,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerPriority.adapter = adapter
        var priority = ""
        binding.spinnerPriority.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                priority = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        binding.apply {
            btnAdd.setOnClickListener {
                val taskName = binding.etTaskName.text.toString().trim()
                val desc = binding.etTaskDesc.text.toString().trim()

                if (taskName.isBlank()) {
                    val msg = getString(R.string.empty_task)
                    ToastUtil().toast(msg,requireContext())
                    return@setOnClickListener
                }
                val todoEntry = ToDoTask(
                    0,
                    taskName,
                    desc,
                    priority
                )
                viewModel.insert(todoEntry)
                val msg: String = getString(R.string.task_added_successfully)
                ToastUtil().toast(msg,requireContext())
                findNavController().navigate(R.id.action_addFragment_to_todoTask)
            }

        }
        return binding.root
    }

}