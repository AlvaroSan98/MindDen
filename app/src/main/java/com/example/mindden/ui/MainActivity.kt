package com.example.mindden.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.mindden.data.RetrofitServiceFactory
import com.example.mindden.databinding.ActivityMainBinding
import com.example.mindden.ui.adapters.UserItemAdapter
import com.example.mindden.data.model.Result
import com.example.mindden.ui.adapters.UserClickedListener
import com.example.mindden.utils.Constants
import com.google.gson.Gson
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var userList = ArrayList<Result>(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val recyclerView = binding.userListRv
        val userItemAdapter = UserItemAdapter(
            emptyList(),
            object: UserClickedListener{
                override fun onUserClickedListener(user: Result) {
                    val intent = Intent(this@MainActivity, UserDetailsActivity::class.java).apply{
                        putExtra(Constants.USER_DATA_JSON, Gson().toJson(user))
                    }
                    startActivity(intent)
                }
            },
        )
        recyclerView.adapter = userItemAdapter

        obtenerUsuarios(recyclerView.adapter as UserItemAdapter)

    }

    fun obtenerUsuarios(userItemAdapter: UserItemAdapter) {
        val service = RetrofitServiceFactory.makeRetrofitService()

        lifecycleScope.launch {
            for (i in 0..10) {
                try {
                    val result = service.listUserData().results[0]
                    if(!userList.contains(result)) {
                        userList.add(result)
                    }
                } catch (exception: NumberFormatException) {
                    exception.printStackTrace()
                }
            }
            userItemAdapter.users = userList
            userItemAdapter.notifyDataSetChanged()
        }
    }
}


