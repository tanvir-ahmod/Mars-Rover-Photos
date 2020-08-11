package com.example.marsroverimages.data.Source.remote

import com.example.marsroverimages.models.RoverData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RoverImageService {
    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun getImages(
        @Query("sol")
        sol : String = "1000",
        @Query("api_key")
        apiKey: String = "DEMO_KEY",
        @Query("page")
        page: Int = 1
    ): Response<RoverData>
}