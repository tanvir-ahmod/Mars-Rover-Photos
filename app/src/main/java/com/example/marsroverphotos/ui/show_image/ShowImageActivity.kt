package com.example.marsroverphotos.ui.show_image

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import com.example.marsroverphotos.R
import com.example.marsroverphotos.base.ui.BaseActivity
import com.example.marsroverphotos.databinding.ActivityShowImageBinding
import com.example.marsroverphotos.models.QueryModel
import com.example.marsroverphotos.models.RoverPhoto
import com.example.marsroverphotos.ui.rover_selection.RoverSelectionActivity
import com.example.marsroverphotos.utills.components.ImageFilterChip
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
        setContent {
            MaterialTheme {
                Column(modifier = Modifier.padding(bottom = 8.dp)) {
                    SetUpToolBar()
                }
            }
        }

        val queryModel =
            intent?.getSerializableExtra(RoverSelectionActivity.QUERY_MODEL) as QueryModel
        mViewModel.setQueryModel(queryModel)
        mViewModel.getImages()
    }

    @Composable
    private fun SetUpToolBar() {
        Surface(elevation = 8.dp) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.align(Alignment.CenterStart)) {
                    IconButton(
                        onClick = { onBackPressed() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                        )
                    }

                    if (mViewModel.selectedDateText.value.isEmpty() && mViewModel.selectedCameraName.value.isEmpty()) {
                        ImageFilterChip(
                            text = getString(R.string.no_filter),
                            hasAction = false
                        ) {}
                    }
                    if (mViewModel.selectedDateText.value.isNotEmpty()) {
                        ImageFilterChip(
                            text = mViewModel.selectedDateText.value,
                            hasAction = true
                        ) {
                            mViewModel.clearDateFilter()
                        }
                    }
                    if (mViewModel.selectedCameraName.value.isNotEmpty()) {
                        ImageFilterChip(
                            text = mViewModel.selectedCameraName.value,
                            hasAction = true
                        ) {
                            mViewModel.clearCameraFilter()
                        }
                    }
                }

                IconButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = { showBottomSheet() }) {
                    Icon(
                        imageVector = vectorResource(id = R.drawable.ic_filter),
                    )
                }
            }
        }
    }

    private fun setUpObservers() {
        mViewModel.images.observe(this, Observer { result ->
            roverImageAdapter.addPhotos(result)
            mViewBinding.rvImages.scheduleLayoutAnimation()
        })

        mViewModel.showImageDetailsDialog.observe(this, Observer { isShowDialog ->
            if (isShowDialog)
                imageDetailsDialog.show(supportFragmentManager, imageDetailsDialog.tag)
        })
    }

    private fun showImageDetails(roverPhoto: RoverPhoto) {
        mViewModel.showImageDetails(roverPhoto)
    }

    private fun showBottomSheet() {
        val filterOptionDialogFragment = FilterOptionBottomSheet()
        filterOptionDialogFragment.show(
            supportFragmentManager,
            filterOptionDialogFragment.tag
        )
    }
}