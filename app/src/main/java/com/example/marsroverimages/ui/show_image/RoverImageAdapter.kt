package com.example.marsroverimages.ui.show_image

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marsroverimages.databinding.ItemRoverImageBinding
import com.example.marsroverimages.models.RoverPhoto

class RoverImageAdapter(private val onItemClicked: (RoverPhoto) -> Unit) :
    RecyclerView.Adapter<RoverImageAdapter.CameraHolder>() {

    private var roverPhotos: List<RoverPhoto> = arrayListOf()

    fun addPhotos(roverPhotos: List<RoverPhoto>) {
        this.roverPhotos = roverPhotos
        notifyDataSetChanged()
    }

    class CameraHolder(private val binding: ItemRoverImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(roverPhoto: RoverPhoto, onItemClicked: (RoverPhoto) -> Unit) {

            Glide.with(binding.ivCamera.context).load(roverPhoto.img_src).into(binding.ivCamera)

            binding.rootLayout.setOnClickListener {
                onItemClicked(roverPhoto)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CameraHolder {

        val binding =
            ItemRoverImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CameraHolder(binding)
    }

    override fun getItemCount(): Int = roverPhotos.size

    override fun onBindViewHolder(holder: CameraHolder, position: Int) {
        val roverPhoto = roverPhotos[position]
        holder.bindData(roverPhoto, onItemClicked)
    }
}