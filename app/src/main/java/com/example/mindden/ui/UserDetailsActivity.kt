package com.example.mindden.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mindden.R
import com.example.mindden.data.model.Result
import com.example.mindden.databinding.UserDetailsActivityBinding
import com.example.mindden.ui.adapters.DetailViewItem
import com.example.mindden.ui.adapters.UserDetailsAdapter
import com.example.mindden.utils.Constants
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.gson.Gson
import java.text.SimpleDateFormat

class UserDetailsActivity: AppCompatActivity() {

    private val listOfDetails = ArrayList<DetailViewItem>()
    private lateinit var userData: com.example.mindden.data.model.Result
    private lateinit var adapter: UserDetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = UserDetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userData = Gson().fromJson(intent.getStringExtra(Constants.USER_DATA_JSON), Result::class.java)

        Glide.with(binding.root.context).load(userData.picture.large).into(binding.userDetailAvatarIv)

        listOfDetails.add(DetailViewItem.StringDetailsViewItem(getDrawable(R.drawable.user_circle), getString(R.string.full_name), userData.name.first + " " + userData.name.last))

        listOfDetails.add(DetailViewItem.StringDetailsViewItem(getDrawable(R.drawable.email), getString(R.string.email), userData.email))

        if (userData.gender.equals("male")) listOfDetails.add(DetailViewItem.StringDetailsViewItem(getDrawable(R.drawable.male), getString(R.string.gender), getString(R.string.male)))
        else listOfDetails.add(DetailViewItem.StringDetailsViewItem(getDrawable(R.drawable.female), getString(R.string.gender), getString(R.string.female)))

        val dateString = userData.registered.date
        val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val output = SimpleDateFormat("dd/MM/yyyy")
        val date = input.parse(dateString)

        listOfDetails.add(DetailViewItem.StringDetailsViewItem(getDrawable(R.drawable.calendar), getString(R.string.sign_in_date), output.format(date)))

        listOfDetails.add(DetailViewItem.StringDetailsViewItem(getDrawable(R.drawable.phone), getString(R.string.phone), userData.phone))

        val mapDetail = DetailViewItem.MapDetailsViewItem(getString(R.string.address), userData.location.coordinates.latitude.toDouble(), userData.location.coordinates.longitude.toDouble())
        listOfDetails.add(mapDetail)

        adapter = UserDetailsAdapter(listOfDetails, supportFragmentManager)
        binding.userDetailsRv.adapter = adapter
        adapter.notifyDataSetChanged()
    }

}