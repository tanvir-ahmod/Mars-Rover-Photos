package com.example.marsroverimages.ui.show_image

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsroverimages.data.Result
import com.example.marsroverimages.data.Source.RoverRepository
import com.example.marsroverimages.models.RoverData
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ShowImageViewModel @ViewModelInject constructor(private val roverRepository: RoverRepository) :
    ViewModel() {

    private val _fetchImages = MutableLiveData<Result<RoverData>>()
    val images: LiveData<Result<RoverData>> = _fetchImages

    init {
        getImages()
    }

    private fun getImages() {
        viewModelScope.launch {
            val images = roverRepository.getImages("1000", "DEMO_KEY", 1)

            images.onEach { result ->
                _fetchImages.value = result
            }.launchIn(viewModelScope)

        }
    }
}