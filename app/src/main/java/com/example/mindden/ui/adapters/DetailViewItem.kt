package com.example.mindden.ui.adapters

import android.graphics.drawable.Drawable

sealed class DetailViewItem {

    class StringDetailsViewItem (
        val img: Drawable?,
        val title: String,
        val info: String
    ): DetailViewItem()

    class MapDetailsViewItem(
        val title: String,
        val latitude: Double,
        val longitude: Double
    ): DetailViewItem()

}