package com.example.marsroverphotos.data.source

import com.example.marsroverphotos.data.Result
import com.example.marsroverphotos.models.Camera
import com.example.marsroverphotos.models.Rover
import com.example.marsroverphotos.models.RoverData
import kotlinx.coroutines.flow.Flow

interface RoverRepository {
    suspend fun getRovers(): Result<List<Rover>>
    suspend fun getAvailableCameras(roverId: Int): Result<List<Camera>>
    suspend fun getImages(
        name : String,
        sol: String?,
        apiKey: String?,
        camera: String?,
        earthDate: String?,
        page: Int?
    ): Flow<Result<RoverData>>
}