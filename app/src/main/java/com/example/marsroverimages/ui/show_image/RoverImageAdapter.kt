package com.example.marsroverimages.ui.show_image

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marsroverimages.R
import com.example.marsroverimages.models.RoverPhoto
import kotlinx.android.synthetic.main.item_rover_camera.view.iv_camera
import kotlinx.android.synthetic.main.item_rover_image.view.*


class RoverImageAdapter(private val roverPhotos: List<RoverPhoto>) :
    RecyclerView.Adapter<RoverImageAdapter.CameraHolder>() {

    var communicator: Communicator? = null

    interface Communicator {
        fun clicked(roverPhoto: RoverPhoto)
    }

    class CameraHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val rootLayout = view.root_layout

        fun bindData(rover: RoverPhoto) {

            Glide.with(view.iv_camera.context).load(rover.img_src).into(view.iv_camera)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CameraHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rover_image, parent, false)
        return CameraHolder(inflatedView)
    }

    override fun getItemCount(): Int = roverPhotos.size

    override fun onBindViewHolder(holder: CameraHolder, position: Int) {
        val roverPhoto = roverPhotos[position]
        holder.bindData(roverPhoto)
        holder.rootLayout.setOnClickListener {
            communicator?.clicked(roverPhoto)
        }
    }
}