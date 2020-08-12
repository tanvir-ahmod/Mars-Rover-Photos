package com.example.marsroverimages.ui.rover_selection

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.marsroverimages.data.Result
import com.example.marsroverimages.data.source.RoverRepository
import com.example.marsroverimages.models.Camera
import com.example.marsroverimages.models.QueryModel
import com.example.marsroverimages.models.Rover
import kotlinx.coroutines.launch

class RoverSelectionViewModel @ViewModelInject constructor(private val roverRepository: RoverRepository) :
    ViewModel() {

    private lateinit var selectedRover: Rover

    private val _rovers = MutableLiveData<List<Rover>>()
    val rovers: LiveData<List<Rover>> = _rovers

    private val _showAvailableCameraDialog = MutableLiveData<Boolean>(false)
    val showAvailableCameraDialog = _showAvailableCameraDialog

    private val _availableCameras = MutableLiveData<List<Camera>>()
    val availableCameras: LiveData<List<Camera>> = _availableCameras

    private val _gotoNextActivity = MutableLiveData<Boolean>()
    val gotoNextActivity: LiveData<QueryModel> = _gotoNextActivity.switchMap { isGotoNextActivity ->
        val model = QueryModel()
        if (isGotoNextActivity) {
            model.name = selectedRover.name
            model.camera = selectedRover.camera
        }
        return@switchMap MutableLiveData<QueryModel>(model)
    }


    init {
        getRovers()
    }

    private fun getRovers() {
        viewModelScope.launch {
            val roversData = roverRepository.getRovers()
            if (roversData is Result.Success) {
                _rovers.value = roversData.data
            }
        }
    }

    fun setSelectedRover(position: Int) {
        _rovers.value?.let {
            selectedRover = it[position]
        }
    }

    fun showAvailableCamera() {
        viewModelScope.launch {
            val availableCameraList = roverRepository.getAvailableCameras(selectedRover)
            if (availableCameraList is Result.Success) {
                _availableCameras.value = availableCameraList.data
                _showAvailableCameraDialog.value = true
            }
        }
    }

    fun goToNextActivity(camera: Camera) {
        selectedRover.camera = camera.name
        _gotoNextActivity.value = true
    }
}