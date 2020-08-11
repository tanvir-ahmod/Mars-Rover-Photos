package com.example.marsroverimages.ui.rover_selection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marsroverimages.R
import com.example.marsroverimages.models.Camera
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_select_rover.view.*

class BottomSheetDialog : BottomSheetDialogFragment() {
    private lateinit var adapter: AvailableCameraAdapter


    private val sharedViewModel: RoverSelectionViewModel by activityViewModels()


    @Nullable
    override fun onCreateView(
        @NonNull inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_select_rover, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AvailableCameraAdapter(this::selectCamera)
        view.rv_cameras.layoutManager = GridLayoutManager(context, 3)
        view.rv_cameras.adapter = adapter
        setUpObservers()
    }

    private fun setUpObservers() {
        sharedViewModel.availableCameras.observe(this, Observer { cameras ->
            adapter.addCameras(cameras)
        })
    }

    private fun selectCamera(camera: Camera) {
        dismiss()
        sharedViewModel.goToNextActivity(camera)
    }
}