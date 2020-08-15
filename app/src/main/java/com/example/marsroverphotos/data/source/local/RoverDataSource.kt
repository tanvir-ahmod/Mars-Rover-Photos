package com.example.marsroverphotos.data.source.local

import com.example.marsroverphotos.data.Result
import com.example.marsroverphotos.models.Camera
import com.example.marsroverphotos.models.Rover


interface RoverDataSource {
    suspend fun getRovers(): Result<List<Rover>>
    suspend fun getAvailableCameras(roveId: Int): Result<List<Camera>>
}