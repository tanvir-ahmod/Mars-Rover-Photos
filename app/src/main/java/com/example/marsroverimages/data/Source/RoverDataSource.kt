package com.example.marsroverimages.data.Source

import com.example.marsroverimages.data.Result
import com.example.marsroverimages.models.Rover


interface RoverDataSource {
    suspend fun getRovers(): Result<List<Rover>>
}