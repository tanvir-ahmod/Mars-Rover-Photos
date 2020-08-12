package com.example.marsroverimages.ui.show_image

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.marsroverimages.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_filter.view.*
import java.util.*

class FilterOptionBottomSheet : BottomSheetDialogFragment() {
    private val sharedViewModel: ShowImageViewModel by activityViewModels()
    private val selectedDate = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.iv_date_picker.setOnClickListener {

            val mDatePicker: DatePickerDialog
            mDatePicker = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->

                    selectedDate.set(Calendar.YEAR, selectedYear)
                    selectedDate.set(Calendar.MONTH, selectedMonth)
                    selectedDate.set(Calendar.DAY_OF_MONTH, selectedDay)

                    val selectedDateText = "$selectedYear-$selectedMonth-$selectedDay"
                    view.tv_date.text = selectedDateText

                    sharedViewModel.changeDate(selectedDateText)
                },
                selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH)
            )
            mDatePicker.datePicker.maxDate = Date().time
            mDatePicker.show()
        }
    }
}