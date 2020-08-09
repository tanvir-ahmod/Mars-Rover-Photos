package com.example.marsroverimages.ui.show_image

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marsroverimages.R
import com.example.marsroverimages.models.Camera
import com.example.marsroverimages.models.RoverPhoto
import kotlinx.android.synthetic.main.item_rover_camera.view.*


class RoverImageAdapter(private val roverPhoto: List<RoverPhoto>) :
    RecyclerView.Adapter<RoverImageAdapter.CameraHolder>() {


    class CameraHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(rover: RoverPhoto) {
            Glide.with(view.iv_camera.context).load(rover.img_src).into(view.iv_camera)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CameraHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rover_image, parent, false)
        return CameraHolder(inflatedView)
    }

    override fun getItemCount(): Int = roverPhoto.size

    override fun onBindViewHolder(holder: CameraHolder, position: Int) {
        holder.bindData(roverPhoto[position])
    }
}