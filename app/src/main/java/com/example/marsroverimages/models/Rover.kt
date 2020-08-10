package com.example.marsroverimages.models

import java.io.Serializable

data class Rover  (
    val name:String,
    val image: Int,
    val launchDate: String,
    val landingDate: String,
    val currentStatus: String
) : Serializable