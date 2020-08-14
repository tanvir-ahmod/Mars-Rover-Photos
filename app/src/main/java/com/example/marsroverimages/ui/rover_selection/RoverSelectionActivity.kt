package com.example.marsroverimages.ui.rover_selection

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.marsroverimages.base.ui.BaseActivity
import com.example.marsroverimages.databinding.ActivityRoverSelectionBinding
import com.example.marsroverimages.ui.show_image.ShowImageActivity
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoverSelectionActivity :
    BaseActivity<RoverSelectionViewModel, ActivityRoverSelectionBinding>() {

    companion object {
        const val QUERY_MODEL = "queryModel"
    }

    private lateinit var roverSelectionAdapter: RoverSelectionAdapter
    override val mViewModel: RoverSelectionViewModel by viewModels()

    override fun getViewBinding(): ActivityRoverSelectionBinding =
        ActivityRoverSelectionBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
        init()
        setUpObservers()
    }

    private fun init() {
        mViewBinding.vm = mViewModel
        roverSelectionAdapter = RoverSelectionAdapter(this)
        mViewBinding.pager.apply {
            adapter = roverSelectionAdapter

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    mViewModel.setSelectedRover(position)
                    super.onPageSelected(position)
                }
            })
        }

        TabLayoutMediator(mViewBinding.tabLayout, mViewBinding.pager) { _, _ ->
        }.attach()
    }

    private fun setUpObservers() {
        mViewModel.rovers.observe(this, Observer { rovers ->
            roverSelectionAdapter.addRovers(rovers)
        })

        mViewModel.gotoNextActivity.observe(this, Observer { queryModel ->
            val intent = Intent(this, ShowImageActivity::class.java)
            intent.putExtra(QUERY_MODEL, queryModel)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        })
    }
}