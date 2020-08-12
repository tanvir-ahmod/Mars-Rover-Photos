package com.example.marsroverimages.ui.show_image

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.marsroverimages.data.Result
import com.example.marsroverimages.data.source.RoverRepository
import com.example.marsroverimages.models.Camera
import com.example.marsroverimages.models.QueryModel
import com.example.marsroverimages.models.RoverData
import com.example.marsroverimages.utills.Constants
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ShowImageViewModel @ViewModelInject constructor(private val roverRepository: RoverRepository) :
    ViewModel() {

    private var queryModel = QueryModel()

    private val _fetchImages = MutableLiveData<Result<RoverData>>()
    val images: LiveData<Result<RoverData>> = _fetchImages

    private val _showAvailableCameras = MutableLiveData<Boolean>(false)

    val availableCameras: LiveData<List<Camera>> = _showAvailableCameras.switchMap {isShow->
        if(isShow)
            fetchAvailableCameras()
        else
            MutableLiveData<List<Camera>>(listOf())
    }



    fun setQueryModel(queryModel: QueryModel) {
        this.queryModel = queryModel
    }

    fun getImages() {
        viewModelScope.launch {
            val images = roverRepository.getImages(
                name = queryModel.name,
                apiKey = Constants.API_KEY,
                page = 1,
                sol = queryModel.sol,
                earthDate = queryModel.earthDate,
                camera = queryModel.camera
            )

            images.onEach { result ->
                _fetchImages.value = result
            }.launchIn(viewModelScope)

        }
    }

    fun changeDate(earthDate: String) {
        queryModel.earthDate = earthDate
        queryModel.sol = null
        getImages()
    }

    fun changeCamera(camera: String) {
        queryModel.camera = camera
        getImages()
        changeAvailableCameraShowStatus()
    }

    private fun fetchAvailableCameras() : LiveData<List<Camera>>{
        val availableCameras  = MutableLiveData<List<Camera>>()
        viewModelScope.launch {
            queryModel.roverId?.let {roverId->
                val availableCameraList = roverRepository.getAvailableCameras(roverId)
                if (availableCameraList is Result.Success) {
                    availableCameras.value = availableCameraList.data!!
                }
            }

        }

        return availableCameras
    }

    fun changeAvailableCameraShowStatus()  {
        val cameraStatus  = _showAvailableCameras.value
        _showAvailableCameras.value =  !cameraStatus!!
    }
}