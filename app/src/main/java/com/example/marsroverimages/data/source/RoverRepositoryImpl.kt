package com.example.marsroverimages.data.source

import com.example.marsroverimages.data.Result
import com.example.marsroverimages.data.source.remote.RoverServiceImpl
import com.example.marsroverimages.models.Camera
import com.example.marsroverimages.models.Rover
import com.example.marsroverimages.models.RoverData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RoverRepositoryImpl @Inject constructor(
    private val roverDataSource: RoverDataSource,
    private val roverImageServiceImpl: RoverServiceImpl
) :
    RoverRepository {
    override suspend fun getRovers(): Result<List<Rover>> = roverDataSource.getRovers()
    override suspend fun getAvailableCameras(rover: Rover): Result<List<Camera>> =
        roverDataSource.getAvailableCameras(rover)


    override suspend fun getImages(
        name : String,
        sol: String,
        apiKey: String,
        camera: String,
        page: Int
    ): Flow<Result<RoverData>> = flow {
        val images = roverImageServiceImpl.getImages(name, sol, apiKey, camera, page)

        if (images.isSuccessful)
            images.body()?.let { roverData ->
                emit(Result.Success(roverData))
            }
        emit(Result.Error(Exception()))
    }
}