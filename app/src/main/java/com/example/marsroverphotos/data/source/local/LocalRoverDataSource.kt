package com.example.marsroverphotos.data.source.local

import com.example.marsroverphotos.R
import com.example.marsroverphotos.data.Result
import com.example.marsroverphotos.data.Result.Success
import com.example.marsroverphotos.models.Camera
import com.example.marsroverphotos.models.Rover
import javax.inject.Inject

class LocalRoverDataSource @Inject constructor() :
    RoverDataSource {

    private val rovers: ArrayList<Rover> = arrayListOf()

    init {
        addRover(Rover(1, "Curiosity", R.drawable.curiosity, "2011-11-26", "2012-08-06", "Active"))
        addRover(
            Rover(
                2, "Opportunity",
                R.drawable.opportunity,
                "2003-07-07",
                "2004-01-25",
                "Complete"
            )
        )
        addRover(Rover(3, "Spirit", R.drawable.spirit, "2003-06-10", "2004-01-04", "Complete"))
    }

    private fun addRover(rover: Rover) {
        rovers.add(rover)
    }

    override suspend fun getRovers(): Result<List<Rover>> = Success(rovers)
    override suspend fun getAvailableCameras(roverId: Int): Result<List<Camera>> {

        val availableCameras: ArrayList<Camera> = arrayListOf()

        when (roverId) {
            1 -> {
                availableCameras.add(Camera("FHAZ", R.drawable.curiosity_fhaz))
                availableCameras.add(Camera("RHAZ", R.drawable.curiosity_rhaz))
                availableCameras.add(Camera("MAST", R.drawable.curiosity_mast))
                availableCameras.add(Camera("CHEMCAM", R.drawable.curiosity_chemcam))
                availableCameras.add(Camera("NAVCAM", R.drawable.curiosity_navcam))
            }
            2 -> {
                availableCameras.add(Camera("PANCAM", R.drawable.opportunity_pancam))
                availableCameras.add(Camera("NAVCAM", R.drawable.opportunity_navcam))
            }
            3 -> {
                availableCameras.add(Camera("PANCAM", R.drawable.spirit_pancam))
                availableCameras.add(Camera("NAVCAM", R.drawable.spirit_navcam))
            }
            else -> {
            }
        }

        return Success(availableCameras)
    }

}