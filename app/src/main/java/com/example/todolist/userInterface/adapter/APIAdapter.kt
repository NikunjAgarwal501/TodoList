package com.example.todolist.userInterface.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.local.entities.APIResponseModelItem
import com.example.todolist.databinding.EachDataApiBinding
import com.squareup.picasso.Picasso

class APIAdapter(private var users: List<APIResponseModelItem>):RecyclerView.Adapter<APIAdapter.APIViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): APIViewHolder {
        val binding = EachDataApiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return APIViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: APIViewHolder, position: Int) {
        holder.bind(users[position])
    }
    class APIViewHolder(val binding: EachDataApiBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(user: APIResponseModelItem) {

            binding.tvName.text = user.login
            binding.tvId.text = user.id.toString()
            Picasso.get()
                .load(user.avatar_url)
                .into(binding.ivUserImg)
        }
    }

}