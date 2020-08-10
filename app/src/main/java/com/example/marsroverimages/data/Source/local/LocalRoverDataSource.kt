package com.example.marsroverimages.data.Source.local

import com.example.marsroverimages.R
import com.example.marsroverimages.data.Result
import com.example.marsroverimages.data.Result.Success
import com.example.marsroverimages.data.Source.RoverDataSource
import com.example.marsroverimages.models.Rover

class LocalRoverDataSource : RoverDataSource {

    val rovers: ArrayList<Rover> = arrayListOf()

    init {
        addRover(Rover("Curiosity", R.drawable.curiosity, "2011-11-26", "2012-08-06", "Active"))
        addRover(
            Rover(
                "Opportunity",
                R.drawable.opportunity,
                "2003-07-07",
                "2004-01-25",
                "Complete"
            )
        )
        addRover(Rover("Spirit", R.drawable.spirit, "2003-06-10", "2004-01-04", "Complete"))
    }

    private fun addRover(rover: Rover) {
        rovers.add(rover)
    }

    override suspend fun getRovers(): Result<List<Rover>> = Success(rovers)

}