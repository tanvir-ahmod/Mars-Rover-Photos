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
import com.example.marsroverimages.data.Result
import com.example.marsroverimages.models.QueryModel
import com.example.marsroverimages.models.RoverPhoto
import com.example.marsroverimages.ui.rover_selection.RoverSelectionActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_show_image.*

@AndroidEntryPoint
class ShowImageActivity : BaseActivity<ShowImageViewModel>() {

    override val mViewModel: ShowImageViewModel by viewModels()
    private val roverImageAdapter = RoverImageAdapter(this::showImageDetails)
    private val imageDetailsDialog = ImageDetailsDialog()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_image)
        val queryModel =
            intent?.getSerializableExtra(RoverSelectionActivity.QUERY_MODEL) as QueryModel
        mViewModel.setQueryModel(queryModel)
        mViewModel.getImages()

        initUI()
        setUpObservers()
    }

    private fun initUI() {
        rv_images.layoutManager = GridLayoutManager(this, 2)
        rv_images.adapter = roverImageAdapter
    }

    private fun setUpObservers() {
        mViewModel.images.observe(this, Observer { result ->
            if (result is Result.Success) {
                roverImageAdapter.addPhotos(result.data.photos)
            }
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

}