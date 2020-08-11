package com.example.marsroverimages.data.Source.remote

import javax.inject.Inject


class RoverServiceImpl @Inject constructor(private val roverImageService: RoverImageService) {
    suspend fun getImages(sol: String, apiKey: String, page: Int) =
        roverImageService.getImages(sol, apiKey, page)

}