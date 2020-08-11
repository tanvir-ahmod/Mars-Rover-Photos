package com.example.marsroverimages.ui.rover_selection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marsroverimages.R
import com.example.marsroverimages.models.Camera
import kotlinx.android.synthetic.main.item_rover_camera.view.*


class AvailableCameraAdapter(private val onItemClicked: (Camera) -> Unit) :
    RecyclerView.Adapter<AvailableCameraAdapter.CameraHolder>() {

    private var cameras: List<Camera> = arrayListOf()

    fun addCameras(cameras: List<Camera>) {
        this.cameras = cameras
        notifyDataSetChanged()
    }

    class CameraHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(camera: Camera, onItemClicked: (Camera) -> Unit) {
            view.tv_camera_name.text = camera.name
            Glide.with(view.iv_camera.context).load(camera.image).into(view.iv_camera)

            view.root_layout.setOnClickListener {
                onItemClicked(camera)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CameraHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rover_camera, parent, false)
        return CameraHolder(inflatedView)
    }

    override fun getItemCount(): Int = cameras.size

    override fun onBindViewHolder(holder: CameraHolder, position: Int) {
        holder.bindData(cameras[position], onItemClicked)
    }
}