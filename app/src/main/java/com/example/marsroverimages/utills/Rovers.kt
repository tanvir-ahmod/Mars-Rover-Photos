package com.example.marsroverimages.utills

import com.example.marsroverimages.R
import com.example.marsroverimages.models.Rover

object Rovers {
    private val curiosity =
        Rover("Curiosity", R.drawable.curiosity, "2011-11-26", "2012-08-06", "Active")
    private val opportunity =
        Rover("Opportunity", R.drawable.opportunity, "2003-07-07", "2004-01-25", "Complete")
    private val spirit =
        Rover("Spirit", R.drawable.opportunity, "2003-06-10", "2004-01-04", "Complete")

    val AVAILABLE_ROVERS: Array<Rover> = arrayOf(curiosity, opportunity, spirit)
}