package com.example.marsroverimages.data.Source

import com.example.marsroverimages.data.Result
import com.example.marsroverimages.data.Source.remote.RoverImageService
import com.example.marsroverimages.models.Camera
import com.example.marsroverimages.models.Rover
import com.example.marsroverimages.models.RoverData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RoverRepositoryImpl @Inject constructor(
    private val roverDataSource: RoverDataSource,
    private val roverImageService: RoverImageService
) :
    RoverRepository {
    override suspend fun getRovers(): Result<List<Rover>> = roverDataSource.getRovers()
    override suspend fun getAvailableCameras(rover: Rover): Result<List<Camera>> =
        roverDataSource.getAvailableCameras(rover)

    override suspend fun getImages(
        sol: String,
        apiKey: String,
        page: Int
    ): Flow<Result<RoverData>> = flow {
        val images = roverImageService.getImages(sol, apiKey, page)

        if (images.isSuccessful)
            images.body()?.let { roverData ->
                emit(Result.Success(roverData))
            }
        emit(Result.Error(Exception()))
    }


}