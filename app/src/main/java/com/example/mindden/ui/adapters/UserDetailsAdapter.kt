package com.example.mindden.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.mindden.R
import com.example.mindden.databinding.MapDetailsBinding
import com.example.mindden.databinding.UserStringDetailsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class UserDetailsAdapter(var users: List<DetailViewItem>, var supportFragmentManager: FragmentManager) : RecyclerView.Adapter<UserDetailsAdapter.UserDetailViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserDetailsAdapter.UserDetailViewHolder {
        return when (viewType) {
            R.layout.user_string_details -> {
                UserDetailViewHolder.StringDetailViewHolder(
                    UserStringDetailsBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false)
                )
            }
            R.layout.map_details -> {
                UserDetailViewHolder.MapDetailViewHolder(
                    MapDetailsBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false)
                )
            }
            else -> throw java.lang.IllegalArgumentException("Illegal ViewType Provided")
        }
    }

    override fun onBindViewHolder(holder: UserDetailsAdapter.UserDetailViewHolder, position: Int) {
        when (holder) {
            is UserDetailViewHolder.StringDetailViewHolder -> holder.bind(users[position] as DetailViewItem.StringDetailsViewItem)
            is UserDetailViewHolder.MapDetailViewHolder -> holder.bind(users[position] as DetailViewItem.MapDetailsViewItem, supportFragmentManager)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (users[position]) {
            is DetailViewItem.StringDetailsViewItem -> R.layout.user_string_details
            is DetailViewItem.MapDetailsViewItem -> R.layout.map_details
        }
    }

    sealed class UserDetailViewHolder(private val binding: ViewBinding): RecyclerView.ViewHolder(binding.root) {

        class StringDetailViewHolder(private val binding: UserStringDetailsBinding): UserDetailViewHolder(binding) {
            fun bind(detailViewItem: DetailViewItem.StringDetailsViewItem) {
                binding.detailTitleTv.text = detailViewItem.title
                binding.detailIconIv.setImageDrawable(detailViewItem.img)
                binding.detailInfoTv.text = detailViewItem.info
            }
        }

        class MapDetailViewHolder(private val binding: MapDetailsBinding): UserDetailViewHolder(binding), OnMapReadyCallback {

            private var latitude: Double? = null
            private var longitude: Double? = null

            fun bind(detailViewItem: DetailViewItem.MapDetailsViewItem, supportFragmentManager: FragmentManager) {
                binding.detailTitleMapTv.text = detailViewItem.title
                val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
                mapFragment.getMapAsync(this)
                latitude = detailViewItem.latitude
                longitude = detailViewItem.longitude
            }

            override fun onMapReady(map: GoogleMap) {
                val position = LatLng(latitude!!, longitude!!)
                map?.addMarker(MarkerOptions().position(position))
                map?.moveCamera(CameraUpdateFactory.newLatLng(position))
            }
        }

    }
}