package com.example.marsroverimages.ui.rover_selection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marsroverimages.R
import com.example.marsroverimages.models.Camera
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_select_rover.view.*

class BottomSheetDialog : BottomSheetDialogFragment() {
    private lateinit var adapter: AvailableCameraAdapter

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

        val dummyAvailableCameras = arrayListOf(
            Camera("FHAZ", R.drawable.fhaz),
            Camera("FHAZ", R.drawable.fhaz),
            Camera("FHAZ", R.drawable.fhaz),
            Camera("FHAZ", R.drawable.fhaz),
            Camera("FHAZ", R.drawable.fhaz),
            Camera("FHAZ", R.drawable.fhaz),
            Camera("FHAZ", R.drawable.fhaz),
            Camera("FHAZ", R.drawable.fhaz),
            Camera("FHAZ", R.drawable.fhaz)
        )

        adapter = AvailableCameraAdapter(dummyAvailableCameras)
        view.rv_cameras.layoutManager = GridLayoutManager(context, 3)
        view.rv_cameras.adapter = adapter
    }
}