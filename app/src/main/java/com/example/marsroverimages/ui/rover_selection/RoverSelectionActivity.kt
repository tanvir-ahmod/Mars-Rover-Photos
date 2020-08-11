package com.example.marsroverimages.ui.rover_selection

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.marsroverimages.R
import com.example.marsroverimages.base.ui.BaseActivity
import com.example.marsroverimages.ui.show_image.ShowImageActivity
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
        pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mViewModel.setSelectedRover(position)
            }
        })

        TabLayoutMediator(tab_layout, pager) { _, _ ->
        }.attach()

        btn_select.setOnClickListener {
            mViewModel.showAvailableCamera()
        }
    }

    private fun setUpObservers() {
        mViewModel.rovers.observe(this, Observer { rovers ->
            roverSelectionAdapter.addRovers(rovers)
        })

        mViewModel.showAvailableCameraDialog.observe(this, Observer { isShow ->
            if (isShow) {
                val bottomSheetFragment = BottomSheetDialog()
                bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
            }
        })
        mViewModel.gotoNextActivity.observe(this, Observer { isStartActivity ->
            if (isStartActivity) {
              startActivity(Intent(this, ShowImageActivity::class.java))
            }
        })
    }
}