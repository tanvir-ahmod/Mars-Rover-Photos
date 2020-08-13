package com.example.marsroverimages.ui.show_image

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marsroverimages.databinding.DialogFilterBinding
import com.example.marsroverimages.ui.rover_selection.AvailableCameraAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

class FilterOptionBottomSheet : BottomSheetDialogFragment() {
    private val availableCameraAdapter = AvailableCameraAdapter(this::selectCamera)
    private val sharedViewModel: ShowImageViewModel by activityViewModels()
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

       /* mViewBinding.btnChooseCamera.setOnClickListener {
            sharedViewModel.changeAvailableCameraShowStatus()
        }*/

        mViewBinding.rvCameras.apply {
            this.layoutManager = GridLayoutManager(context, 3)
            this.adapter = availableCameraAdapter
        }
    }


    private fun setUpObservers() {
        sharedViewModel.availableCameras.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { cameras ->
                availableCameraAdapter.addCameras(cameras)
            })

        sharedViewModel.showDatePicker.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { date ->
                date?.let {
                    showDatePicker(date)
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