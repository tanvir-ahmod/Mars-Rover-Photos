package com.example.marsroverimages.ui.rover_selection

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.marsroverimages.R
import com.example.marsroverimages.base.ui.BaseActivity
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_rover_selection.*

@AndroidEntryPoint
class RoverSelectionActivity : BaseActivity<RoverSelectionViewModel>() {
    private lateinit var roverSelectionAdapter: RoverSelectionAdapter
    override val mViewModel: RoverSelectionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rover_selection)
        init()
        setUpObservers()

    }

    private fun init() {
        roverSelectionAdapter = RoverSelectionAdapter(this)
        pager.adapter = roverSelectionAdapter
        TabLayoutMediator(tab_layout, pager) { _, _ ->
        }.attach()

        btn_select.setOnClickListener {
            val bottomSheetFragment = BottomSheetDialog()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }
    }

    private fun setUpObservers() {
        mViewModel.rovers.observe(this, Observer { rovers ->
            roverSelectionAdapter.addRovers(rovers)
        })
    }


}