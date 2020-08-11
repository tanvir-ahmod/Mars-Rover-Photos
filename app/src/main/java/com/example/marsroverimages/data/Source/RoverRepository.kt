package com.example.marsroverimages.data.Source

import com.example.marsroverimages.data.Result
import com.example.marsroverimages.models.Camera
import com.example.marsroverimages.models.Rover

interface RoverRepository {
    suspend fun getRovers(): Result<List<Rover>>
    suspend fun getAvailableCameras(rover: Rover): Result<List<Camera>>
}