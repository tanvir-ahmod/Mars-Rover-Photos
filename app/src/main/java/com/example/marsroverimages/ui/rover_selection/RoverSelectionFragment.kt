package com.example.marsroverimages.ui.rover_selection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.marsroverimages.R
import com.example.marsroverimages.base.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoverSelectionFragment : BaseFragment() {

    companion object {
        const val ARG_POSITION = "position"

        fun getInstance(position: Int): Fragment {
            val roverSelectionFragment = RoverSelectionFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_POSITION, position)
            roverSelectionFragment.arguments = bundle
            return roverSelectionFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rover_selection, container, false)
    }
}