package com.example.marsroverimages.ui.rover_selection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.marsroverimages.R
import com.example.marsroverimages.base.ui.BaseFragment
import com.example.marsroverimages.models.Rover
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_rover_selection.view.*

@AndroidEntryPoint
class RoverSelectionFragment : BaseFragment() {

    lateinit var rover: Rover

    companion object {
        const val ARG_ROVER = "rover"

        fun getInstance( rover: Rover): Fragment {
            val roverSelectionFragment = RoverSelectionFragment()
            val bundle = Bundle()
            bundle.putSerializable(ARG_ROVER, rover)
            roverSelectionFragment.arguments = bundle
            return roverSelectionFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rover = arguments?.get(ARG_ROVER) as Rover
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rover_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireContext()).load(rover.image).into(view.iv_rover)
        view.tv_name.text = rover.name
        view.tv_launch_date.text = rover.launchDate
        view.tv_landing_date.text = rover.landingDate
        view.tv_status.text = rover.currentStatus
    }
}