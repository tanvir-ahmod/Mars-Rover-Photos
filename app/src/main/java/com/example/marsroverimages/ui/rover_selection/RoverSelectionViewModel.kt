package com.example.marsroverimages.ui.rover_selection

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsroverimages.data.Result
import com.example.marsroverimages.data.Source.RoverRepository
import com.example.marsroverimages.models.Rover
import kotlinx.coroutines.launch

class RoverSelectionViewModel @ViewModelInject constructor(private val roverRepository: RoverRepository) :
    ViewModel() {

    private val _rovers = MutableLiveData<List<Rover>>()
    val rovers: LiveData<List<Rover>> = _rovers

    init {
        getRovers()
    }

    private fun getRovers() {
        viewModelScope.launch {
            val roversData = roverRepository.getRovers()
            if (roversData is Result.Success)
                _rovers.value = roversData.data
        }
    }
}