
package com.example.todolist.userInterface.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityApiBinding
import com.example.todolist.network.NetworkClient
import com.example.todolist.data.repository.APIRepository
import com.example.todolist.userInterface.adapter.APIAdapter
import com.example.todolist.viewmodel.APIViewModel
import com.example.todolist.viewmodel.APIViewModelFactory

class APIActivity : AppCompatActivity() {
    private lateinit var binding: ActivityApiBinding
    private val networkClient = NetworkClient()
    private val repository = APIRepository(networkClient)
    private val viewModel: APIViewModel by viewModels { APIViewModelFactory(repository) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvApi.layoutManager = LinearLayoutManager(this)

        // Initialize ViewModel and Repository
        viewModel.users.observe(this) { users ->
            if (users != null) {
                binding.rvApi.adapter = APIAdapter(users)
            }
        }
        viewModel.loading.observe(this) { isLoading ->
            if (isLoading) {
                binding.loader.visibility = View.VISIBLE
            } else {
                binding.loader.visibility = View.GONE
            }
        }

        viewModel.fetchUsers(this)

    }
}
