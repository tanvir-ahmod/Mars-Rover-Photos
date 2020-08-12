package com.example.marsroverimages.ui.show_image

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsroverimages.data.Result
import com.example.marsroverimages.data.source.RoverRepository
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


    fun setQueryModel(queryModel: QueryModel) {
        this.queryModel = queryModel
    }

    fun getImages() {
        viewModelScope.launch {
            Log.d("queryModel", "camera ${queryModel.camera}")
            val images = roverRepository.getImages(
                name = queryModel.name,
                apiKey = Constants.API_KEY,
                page = 1,
                sol = "1000",
                camera = queryModel.camera
            )

            images.onEach { result ->
                _fetchImages.value = result
            }.launchIn(viewModelScope)

        }
    }
}