package com.example.marsroverimages.ui.rover_selection

import android.os.Bundle
import com.example.marsroverimages.R
import com.example.marsroverimages.base.ui.BaseActivity
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_rover_selection.*

@AndroidEntryPoint
class RoverSelectionActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rover_selection)

        val roverSelectionAdapter = RoverSelectionAdapter(this, 3)
        pager.adapter = roverSelectionAdapter

        TabLayoutMediator(tab_layout, pager) { _, _ ->
        }.attach()

        btn_select.setOnClickListener {
            val bottomSheetFragment = BottomSheetDialog()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }
    }
}