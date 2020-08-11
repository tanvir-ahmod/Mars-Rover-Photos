package com.example.marsroverimages.models

import java.io.Serializable

data class Rover  (
    val id : Int,
    val name:String,
    val image: Int,
    val launchDate: String,
    val landingDate: String,
    val currentStatus: String
) : Serializable