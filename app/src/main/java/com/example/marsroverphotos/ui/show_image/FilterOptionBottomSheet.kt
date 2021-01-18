package com.example.marsroverphotos.ui.show_image

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.activityViewModels
import com.example.marsroverphotos.R
import com.example.marsroverphotos.models.Camera
import com.example.marsroverphotos.utills.components.LazyGridFor
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

class FilterOptionBottomSheet : BottomSheetDialogFragment() {
    private val sharedViewModel: ShowImageViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    ShowFilterDialog()
                }
            }
        }
    }

    @Composable
    private fun ShowFilterDialog() {
        Column(modifier = Modifier.padding(16.dp)) {
            ShowDatePickerUI()
            ShowCameraChangeUI()
            ShowAvailableCameras()
        }
    }

    @Composable
    private fun ShowDatePickerUI() {
        Row(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)) {
            Text(
                getString(R.string.date), modifier = Modifier.weight(2F),
                fontSize = 18.sp,
            )
            Text(
                sharedViewModel.selectedDateText.get().toString(),
                modifier = Modifier.weight(2F),
                fontSize = 18.sp,
            )

            val showDatePicker: Calendar? by sharedViewModel.showDatePicker.observeAsState()
            showDatePicker?.let {
                showDatePicker(it)
            }
            IconButton(modifier = Modifier.then(Modifier.preferredSize(24.dp)).weight(1F),
                onClick = { sharedViewModel.showDatePicker() }) {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    tint = colorResource(id = R.color.colorAccent)
                )
            }
        }
    }

    @Composable
    private fun ShowCameraChangeUI() {
        Row(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)) {
            Text(
                getString(R.string.camera), modifier = Modifier.fillMaxWidth().weight(2F),
                fontSize = 18.sp,
            )
            Text(
                sharedViewModel.selectedCameraName.get().toString(),
                modifier = Modifier.fillMaxWidth().weight(2F),
                fontSize = 18.sp,
            )

            val isChangeCloseButton: Boolean by sharedViewModel.changeBottomSheetCloseButton.observeAsState(
                false
            )
            var buttonText = getString(R.string.change)
            var buttonColor = colorResource(id = R.color.colorAccent)
            if (isChangeCloseButton) {
                buttonText = getString(R.string.close)
                buttonColor = Color.Red
            }

            Button(colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
                modifier = Modifier.fillMaxWidth().height(40.dp).weight(1F),
                shape = RoundedCornerShape(30),
                onClick = { sharedViewModel.changeAvailableCameraShowStatus() }) {
                Text(
                    buttonText,
                    color = Color.White,
                    fontSize = 10.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    @Composable
    private fun ShowAvailableCameras() {
        val availableCameras: List<Camera>? by sharedViewModel.availableCameras.observeAsState()
        availableCameras?.let { cameras ->
            LazyGridFor(cameras, 3) { item ->
                Card(
                    elevation = 4.dp,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.padding(8.dp)
                        .clickable(onClick = {
                            selectCamera(item.name)
                        })

                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Image(
                            bitmap = imageResource(id = item.image),
                            alignment = Alignment.Center,
                            modifier = Modifier
                                .preferredWidth(100.dp)
                                .preferredHeight(100.dp),
                            contentScale = ContentScale.Crop,
                        )
                        Text(
                            text = item.name,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }

    private fun showDatePicker(date: Calendar) {
        val mDatePicker = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
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
    }
}