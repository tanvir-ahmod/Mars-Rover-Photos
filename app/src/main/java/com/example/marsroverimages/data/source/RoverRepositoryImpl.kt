package com.example.marsroverimages.data.source

import com.example.marsroverimages.data.Result
import com.example.marsroverimages.data.source.local.LocalRoverDataSource
import com.example.marsroverimages.data.source.remote.RoverServiceImpl
import com.example.marsroverimages.models.Camera
import com.example.marsroverimages.models.Rover
import com.example.marsroverimages.models.RoverData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RoverRepositoryImpl @Inject constructor(
    private val roverDataSource: LocalRoverDataSource,
    private val roverImageServiceImpl: RoverServiceImpl
) :
    RoverRepository {
    override suspend fun getRovers(): Result<List<Rover>> = roverDataSource.getRovers()
    override suspend fun getAvailableCameras(roverId: Int): Result<List<Camera>> =
        roverDataSource.getAvailableCameras(roverId)

    override suspend fun getImages(
        name: String,
        sol: String?,
        apiKey: String?,
        camera: String?,
        earthDate: String?,
        page: Int?
    ): Flow<Result<RoverData>> = flow {

        try {
            val images = roverImageServiceImpl.getImages(name, sol, apiKey, camera, earthDate, page)

            if (images.isSuccessful) {
                images.body()?.let { roverData ->
                    emit(Result.Success(roverData))
                } ?: run { emit(Result.Error(Exception(images.message()))) }
            } else
                emit(Result.Error(java.lang.Exception(images.message())))
        } catch (e: Exception) {
            emit(Result.Error(java.lang.Exception(e.message)))
        }
    }
}