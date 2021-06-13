package com.example.marsroverphotos.ui.show_image

import androidx.compose.runtime.mutableStateOf
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.marsroverphotos.base.ui.BaseViewModel
import com.example.marsroverphotos.data.Result
import com.example.marsroverphotos.data.source.RoverRepository
import com.example.marsroverphotos.models.Camera
import com.example.marsroverphotos.models.QueryModel
import com.example.marsroverphotos.models.RoverPhoto
import com.example.marsroverphotos.utills.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ShowImageViewModel @Inject constructor(private val roverRepository: RoverRepository) :
    BaseViewModel() {

    private val selectDate = Calendar.getInstance()
    private var queryModel = QueryModel()
    var selectedCameraName = mutableStateOf("")
    var selectedDateText = mutableStateOf("")
    val noImageFound = ObservableField(false)

    private val _fetchImages = MutableLiveData<List<RoverPhoto>>()
    val images: LiveData<List<RoverPhoto>> = _fetchImages

    private val _showImageDetailsDialog = MutableLiveData(false)
    val showImageDetailsDialog: LiveData<Boolean> = _showImageDetailsDialog

    private val _showImageDetails = MutableLiveData<RoverPhoto>()
    val showImageDetails: LiveData<RoverPhoto> = _showImageDetails

    private val _showAvailableCameras = MutableLiveData(false)
    val changeBottomSheetCloseButton: LiveData<Boolean> = _showAvailableCameras
    val availableCameras: LiveData<List<Camera>> = _showAvailableCameras.switchMap { isShow ->
        if (isShow)
            fetchAvailableCameras()
        else
            MutableLiveData(listOf())
    }

    private val _showDatePicker = MutableLiveData(false)
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

            images.collect { result ->
                showLoader.value = false
                if (result is Result.Success) {
                    _fetchImages.value = result.data.photos
                    noImageFound.set(false)
                    if (result.data.photos.isEmpty())
                        noImageFound.set(true)
                } else if (result is Result.Error) {
                    noImageFound.set(true)
                    showErrorMessage.value = result.exception.message.toString()
                }

            }
        }
    }

    fun changeDate(year: Int, month: Int, date: Int) {
        selectDate.set(Calendar.YEAR, year)
        selectDate.set(Calendar.MONTH, month)
        selectDate.set(Calendar.DAY_OF_MONTH, date)

        queryModel.earthDate = "$year-${month + 1}-$date" // month is 0 based index
        selectedDateText.value = "$year-${month + 1}-$date"
        queryModel.sol = null
        _showDatePicker.value = false
        getImages()
    }

    fun changeCamera(camera: String) {
        queryModel.camera = camera
        selectedCameraName.value = camera
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
        selectedDateText.value = ""
        getImages()
    }

    fun clearCameraFilter() {
        queryModel.camera = null
        if (queryModel.earthDate == null)
            queryModel.sol = "1000"
        selectedCameraName.value = ""
        getImages()
    }
}