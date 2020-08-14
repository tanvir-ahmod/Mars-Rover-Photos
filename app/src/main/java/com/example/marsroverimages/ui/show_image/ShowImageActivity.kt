package com.example.marsroverimages.ui.show_image

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marsroverimages.R
import com.example.marsroverimages.base.ui.BaseActivity
import com.example.marsroverimages.databinding.ActivityShowImageBinding
import com.example.marsroverimages.models.QueryModel
import com.example.marsroverimages.models.RoverPhoto
import com.example.marsroverimages.ui.rover_selection.RoverSelectionActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowImageActivity : BaseActivity<ShowImageViewModel, ActivityShowImageBinding>() {

    override val mViewModel: ShowImageViewModel by viewModels()
    private val roverImageAdapter = RoverImageAdapter(this::showImageDetails)
    private val imageDetailsDialog = ImageDetailsDialog()

    override fun getViewBinding(): ActivityShowImageBinding =
        ActivityShowImageBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
        setUpToolBar()
        mViewBinding.vm = mViewModel

        val queryModel =
            intent?.getSerializableExtra(RoverSelectionActivity.QUERY_MODEL) as QueryModel
        mViewModel.setQueryModel(queryModel)
        mViewModel.getImages()

        initUI()
        setUpObservers()
    }

    private fun setUpToolBar() {
        setSupportActionBar(mViewBinding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun initUI() {
        mViewBinding.rvImages.apply {
            layoutManager = GridLayoutManager(this@ShowImageActivity, 2)
            adapter = roverImageAdapter
        }

        mViewBinding.chipCameraName.setOnCloseIconClickListener {
            mViewModel.clearCameraFilter()
        }
        mViewBinding.chipDate.setOnCloseIconClickListener {
            mViewModel.clearDateFilter()
        }
    }

    private fun setUpObservers() {
        mViewModel.images.observe(this, Observer { result ->
                roverImageAdapter.addPhotos(result)
        })

        mViewModel.showImageDetailsDialog.observe(this, Observer { isShowDialog ->
            if (isShowDialog)
                imageDetailsDialog.show(supportFragmentManager, imageDetailsDialog.tag)
        })
    }

    private fun showImageDetails(roverPhoto: RoverPhoto) {
        mViewModel.showImageDetails(roverPhoto)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.filter_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_filter -> {
                val filterOptionDialogFragment = FilterOptionBottomSheet()
                filterOptionDialogFragment.show(
                    supportFragmentManager,
                    filterOptionDialogFragment.tag
                )
                true
            }
            else -> false
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}