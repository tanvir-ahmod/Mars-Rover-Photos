package com.example.marsroverimages.ui.show_image

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.marsroverimages.base.ui.BaseViewModel
import com.example.marsroverimages.data.Result
import com.example.marsroverimages.data.source.RoverRepository
import com.example.marsroverimages.models.Camera
import com.example.marsroverimages.models.QueryModel
import com.example.marsroverimages.models.RoverData
import com.example.marsroverimages.models.RoverPhoto
import com.example.marsroverimages.utills.Constants
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*

class ShowImageViewModel @ViewModelInject constructor(private val roverRepository: RoverRepository) :
    BaseViewModel() {

    private val selectDate = Calendar.getInstance()
    private var queryModel = QueryModel()
    var selectedCameraName = ObservableField<String>("")
    var selectedDateText = ObservableField<String>("")

    private val _fetchImages = MutableLiveData<Result<RoverData>>()
    val images: LiveData<Result<RoverData>> = _fetchImages

    private val _showImageDetailsDialog = MutableLiveData<Boolean>(false)
    val showImageDetailsDialog: LiveData<Boolean> = _showImageDetailsDialog

    private val _showImageDetails = MutableLiveData<RoverPhoto>()
    val showImageDetails: LiveData<RoverPhoto> = _showImageDetails

    private val _showAvailableCameras = MutableLiveData<Boolean>(false)
    val availableCameras: LiveData<List<Camera>> = _showAvailableCameras.switchMap { isShow ->
        if (isShow)
            fetchAvailableCameras()
        else
            MutableLiveData<List<Camera>>(listOf())
    }

    private val _showDatePicker = MutableLiveData<Boolean>(false)
    val showDatePicker: LiveData<Calendar> = _showDatePicker.switchMap { isShow ->
        val datePicker = MutableLiveData<Calendar>(null)
        if (isShow)
            datePicker.value = selectDate

        return@switchMap datePicker
    }

    fun setQueryModel(queryModel: QueryModel) {
        this.queryModel = queryModel
    }

    fun getImages() {
        viewModelScope.launch {
            showLoader.value = true
            val images = roverRepository.getImages(
                name = queryModel.name,
                apiKey = Constants.API_KEY,
                page = 1,
                sol = queryModel.sol,
                earthDate = queryModel.earthDate,
                camera = queryModel.camera
            )

            images.onEach { result ->
                showLoader.value = false
                _fetchImages.value = result
            }.launchIn(viewModelScope)
        }
    }

    fun changeDate(year: Int, month: Int, date: Int) {
        selectDate.set(Calendar.YEAR, year)
        selectDate.set(Calendar.MONTH, month)
        selectDate.set(Calendar.DAY_OF_MONTH, date)

        queryModel.earthDate = "$year-${month + 1}-$date" // month is 0 based index
        selectedDateText.set("$year-$month-$date")
        queryModel.sol = null
        _showDatePicker.value = false
        getImages()
    }

    fun changeCamera(camera: String) {
        queryModel.camera = camera
        selectedCameraName.set(camera)
        getImages()
        changeAvailableCameraShowStatus()
    }

    private fun fetchAvailableCameras(): LiveData<List<Camera>> {
        val availableCameras = MutableLiveData<List<Camera>>()
        viewModelScope.launch {
            queryModel.roverId?.let { roverId ->
                val availableCameraList = roverRepository.getAvailableCameras(roverId)
                if (availableCameraList is Result.Success) {
                    availableCameras.value = availableCameraList.data!!
                }
            }
        }
        return availableCameras
    }

    fun changeAvailableCameraShowStatus() {
        val cameraStatus = _showAvailableCameras.value
        _showAvailableCameras.value = !cameraStatus!!
    }

    fun showImageDetails(rover: RoverPhoto) {
        _showImageDetailsDialog.value = true
        _showImageDetails.value = rover
    }

    fun showDatePicker() {
        _showDatePicker.value = true
    }

    fun clearDateFilter() {
        queryModel.earthDate = null
        if (queryModel.camera == null)
            queryModel.sol = "1000"
        selectedDateText.set("")
        getImages()
    }

    fun clearCameraFilter() {
        queryModel.camera = null
        if (queryModel.earthDate == null)
            queryModel.sol = "1000"
        selectedCameraName.set("")
        getImages()
    }
}