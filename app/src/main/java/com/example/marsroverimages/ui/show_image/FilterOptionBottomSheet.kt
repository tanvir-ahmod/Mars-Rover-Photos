package com.example.marsroverimages.ui.show_image

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marsroverimages.R
import com.example.marsroverimages.databinding.DialogFilterBinding
import com.example.marsroverimages.ui.rover_selection.AvailableCameraAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*
import kotlin.properties.Delegates

class FilterOptionBottomSheet : BottomSheetDialogFragment() {
    private val availableCameraAdapter = AvailableCameraAdapter(this::selectCamera)
    private val sharedViewModel: ShowImageViewModel by activityViewModels()
    private var shortAnimationDuration by Delegates.notNull<Int>()
    private lateinit var mViewBinding: DialogFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewBinding = DialogFilterBinding.inflate(inflater, container, false).apply {
            vm = sharedViewModel
        }
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        setUpObservers()
    }

    private fun initUI() {
        mViewBinding.rvCameras.apply {
            this.layoutManager = GridLayoutManager(context, 3)
            this.adapter = availableCameraAdapter
        }
        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)
    }

    private fun setUpObservers() {
        sharedViewModel.availableCameras.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { cameras ->
                availableCameraAdapter.addCameras(cameras)
                mViewBinding.rvCameras.scheduleLayoutAnimation()
            })

        sharedViewModel.showDatePicker.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { date ->
                date?.let {
                    showDatePicker(date)
                }
            })

        sharedViewModel.changeBottomSheetCloseButton.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { isChange ->
                if (isChange) {
                    mViewBinding.btnChooseCamera.apply {
                        background =
                            ContextCompat.getDrawable(context, R.drawable.rounded_red_button)
                        text = getString(R.string.close)
                        alpha = 0f
                        animate()
                            .alpha(1f)
                            .setDuration(shortAnimationDuration.toLong())
                            .setListener(null)

                    }
                } else {
                    mViewBinding.btnChooseCamera.apply {
                        background =
                            ContextCompat.getDrawable(context, R.drawable.rounded_green_button)
                        text = getString(R.string.camera)
                        alpha = 0f
                        animate()
                            .alpha(1f)
                            .setDuration(shortAnimationDuration.toLong())
                            .setListener(null)

                    }
                }
            })
    }

    private fun showDatePicker(date: Calendar) {
        val mDatePicker = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                sharedViewModel.changeDate(selectedYear, selectedMonth, selectedDay)
                dismiss()
            },
            date.get(Calendar.YEAR),
            date.get(Calendar.MONTH),
            date.get(Calendar.DAY_OF_MONTH)
        )
        mDatePicker.datePicker.maxDate = Date().time
        mDatePicker.show()
    }

    private fun selectCamera(camera: String) {
        sharedViewModel.changeCamera(camera)
        dismiss()
    }
}