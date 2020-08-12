package com.example.marsroverimages.data.source.remote

import javax.inject.Inject

class RoverServiceImpl @Inject constructor(private val roverImageService: RoverImageService) {
    suspend fun getImages(
        name: String,
        sol: String?,
        apiKey: String?,
        camera: String?,
        earthDate: String?,
        page: Int?
    ) =
        roverImageService.getImages(name, sol, apiKey, camera, earthDate, page)

}