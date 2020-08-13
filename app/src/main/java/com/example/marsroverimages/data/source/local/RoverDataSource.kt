package com.example.marsroverimages.data.source.local

import com.example.marsroverimages.data.Result
import com.example.marsroverimages.models.Camera
import com.example.marsroverimages.models.Rover


interface RoverDataSource {
    suspend fun getRovers(): Result<List<Rover>>
    suspend fun getAvailableCameras(roveId: Int): Result<List<Camera>>
}