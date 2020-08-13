package com.example.marsroverimages.ui.rover_selection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.marsroverimages.databinding.FragmentRoverSelectionBinding
import com.example.marsroverimages.models.Rover
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoverSelectionFragment : Fragment() {

    private lateinit var roverModel: Rover
    private lateinit var viewDataBinding: FragmentRoverSelectionBinding

    companion object {
        const val ARG_ROVER = "rover"

        fun getInstance(rover: Rover): Fragment {
            val roverSelectionFragment = RoverSelectionFragment()
            val bundle = Bundle()
            bundle.putSerializable(ARG_ROVER, rover)
            roverSelectionFragment.arguments = bundle
            return roverSelectionFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        roverModel = arguments?.get(ARG_ROVER) as Rover
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentRoverSelectionBinding.inflate(inflater, container, false).apply {
            rover = roverModel
        }
        return viewDataBinding.root
    }
}