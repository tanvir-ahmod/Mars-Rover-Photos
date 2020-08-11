package com.example.marsroverimages.data.Source

import com.example.marsroverimages.data.Result
import com.example.marsroverimages.models.Camera
import com.example.marsroverimages.models.Rover

class RoverRepositoryImpl (private val roverDataSource: RoverDataSource) :
    RoverRepository {
    override suspend fun getRovers(): Result<List<Rover>> = roverDataSource.getRovers()
    override suspend fun getAvailableCameras(rover: Rover): Result<List<Camera>>  = roverDataSource.getAvailableCameras(rover)
}