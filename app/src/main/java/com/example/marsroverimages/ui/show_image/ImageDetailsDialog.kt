package com.example.marsroverimages.ui.show_image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.marsroverimages.R
import kotlinx.android.synthetic.main.dialog_image_details.view.*


class ImageDetailsDialog : DialogFragment() {

    private val sharedViewModel: ShowImageViewModel by activityViewModels()
    private lateinit var imageDetailsView: View
    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        imageDetailsView = inflater.inflate(R.layout.dialog_image_details, container, false)
        return imageDetailsView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()

    }

    private fun setUpObservers() {
        sharedViewModel.showImageDetails.observe(viewLifecycleOwner, Observer { roverPhoto ->
            Glide.with(requireContext()).load(roverPhoto.img_src).into(imageDetailsView.iv_rover)
            imageDetailsView.tv_camera_name.text = roverPhoto.camera.full_name
            imageDetailsView.tv_earth_date.text = roverPhoto.earth_date
        })
    }
}