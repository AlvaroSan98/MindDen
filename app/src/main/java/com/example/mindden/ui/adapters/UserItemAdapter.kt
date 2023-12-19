package com.example.mindden.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mindden.R
import com.example.mindden.data.model.Result
import com.example.mindden.databinding.UserItemBinding

interface UserClickedListener {
    fun onUserClickedListener(user: Result)
}

class UserItemAdapter(var users: List<Result>, private val userClickedListener: UserClickedListener) : RecyclerView.Adapter<UserItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])
        holder.itemView.setOnClickListener {
            userClickedListener.onUserClickedListener(users[position])
        }
    }

    class ViewHolder(private val binding: UserItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(user: Result) {
            Glide.with(binding.root.context).load(user.picture.large).into(binding.userAvatarIv)
            binding.userNameTv.text = user.name.first + " " + user.name.last
            binding.userEmailTv.text = user.email
            binding.chevronRight.visibility = View.VISIBLE
        }

    }
}