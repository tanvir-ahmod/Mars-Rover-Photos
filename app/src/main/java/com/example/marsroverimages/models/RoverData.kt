package com.example.marsroverimages.models

data class RoverData(
    val photos: List<RoverPhoto>
)

data class RoverX(
    val id: Int,
    val landing_date: String,
    val launch_date: String,
    val name: String,
    val status: String
)

data class RoverPhoto(
    val camera: CameraX,
    val earth_date: String,
    val id: Int,
    val img_src: String,
    val rover: RoverX,
    val sol: Int
)

data class CameraX(
    val full_name: String,
    val id: Int,
    val name: String,
    val rover_id: Int
)