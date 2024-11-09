package com.example.todolist.userInterface.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
//import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.R
import com.example.todolist.databinding.FragmentTodoTaskBinding
import com.example.todolist.databinding.RowLayoutBinding
import com.example.todolist.userInterface.activity.APIActivity
import com.example.todolist.userInterface.adapter.TaskAdapter
import com.example.todolist.utils.ToastUtil
import com.example.todolist.viewmodel.TodoViewModel


class TodoTaskFragment : Fragment() {

    private val viewModel : TodoViewModel by viewModels()
    private lateinit var adapter: TaskAdapter
    private var doubleBackToExitPressedOnce = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTodoTaskBinding.inflate(inflater)

        val rowBinding = RowLayoutBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        adapter = TaskAdapter(
            deleteAction = {todoItem->
                viewModel.delete(todoItem)
                val msg = getString(R.string.task_deleted_successfully)
                ToastUtil().toast(msg,requireContext())
            },
            updateAction = { todoItem->
                val action = TodoTaskFragmentDirections.actionTodoTaskToUpdateFragment(todoItem.id)
                findNavController().navigate(action)
            }

        )
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.getAllTasks().observe(viewLifecycleOwner) { tasks ->
            tasks?.let {
                adapter.setTasks(it)
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.loader.visibility = View.VISIBLE // Show loader when loading
            } else {
                binding.loader.visibility = View.GONE // Hide loader when data is loaded
            }
        }
        rowBinding.btnUpdate.setOnClickListener{
            findNavController().navigate(R.id.action_todoTask_to_updateFragment)
        }
        binding.apply {
            recyclerView.adapter = adapter
            floatingActionButton.setOnClickListener{
                findNavController().navigate(R.id.action_todoTask_to_addFragment)
            }
            apiBtn.setOnClickListener{
                val intent = Intent(activity,APIActivity::class.java)
                startActivity(intent)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (doubleBackToExitPressedOnce) {
                    requireActivity().finish() // Exit the app
                } else {
                    doubleBackToExitPressedOnce = true
                    val msg:String = getString(R.string.back)
                    ToastUtil().toast(msg,context!!)
                    requireActivity().window.decorView.postDelayed({
                        doubleBackToExitPressedOnce = false
                    }, 2000)
                }
            }
        })
        return binding.root
    }

}