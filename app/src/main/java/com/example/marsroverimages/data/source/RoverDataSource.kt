package com.example.marsroverimages.data.source

import com.example.marsroverimages.data.Result
import com.example.marsroverimages.models.Camera
import com.example.marsroverimages.models.Rover


interface RoverDataSource {
    suspend fun getRovers(): Result<List<Rover>>
    suspend fun getAvailableCameras(rover: Rover): Result<List<Camera>>
}