package com.example.marsroverimages.data.Source

import com.example.marsroverimages.data.Result
import com.example.marsroverimages.models.Camera
import com.example.marsroverimages.models.Rover
import com.example.marsroverimages.models.RoverData
import kotlinx.coroutines.flow.Flow

interface RoverRepository {
    suspend fun getRovers(): Result<List<Rover>>
    suspend fun getAvailableCameras(rover: Rover): Result<List<Camera>>
    suspend fun getImages(sol: String, apiKey: String, page: Int): Flow<Result<RoverData>>
}