package com.example.marsroverimages.models

data class Rover(
    val name:String,
    val image: Int,
    val launchDate: String,
    val landingDate: String,
    val currentStatus: String
)