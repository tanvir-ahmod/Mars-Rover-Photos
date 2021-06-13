package com.example.marsroverphotos.ui.show_image

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.marsroverphotos.R
import com.example.marsroverphotos.base.ui.BaseActivity
import com.example.marsroverphotos.databinding.ActivityShowImageBinding
import com.example.marsroverphotos.models.QueryModel
import com.example.marsroverphotos.models.RoverPhoto
import com.example.marsroverphotos.ui.rover_selection.RoverSelectionActivity
import com.example.marsroverphotos.utills.components.ImageFilterChip
import com.example.marsroverphotos.utills.components.LazyGridFor
import com.example.marsroverphotos.utills.loadPicture
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowImageActivity : BaseActivity<ShowImageViewModel, ActivityShowImageBinding>() {

    override val mViewModel: ShowImageViewModel by viewModels()
    private val imageDetailsDialog = ImageDetailsDialog()

    override fun getViewBinding(): ActivityShowImageBinding =
        ActivityShowImageBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Column(modifier = Modifier.padding(bottom = 8.dp)) {
                    SetUpToolBar()
                    ShowImages()
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

    @Composable
    private fun ShowImages() {
        val availablePhotos: List<RoverPhoto>? by mViewModel.images.observeAsState()
        availablePhotos?.let { photos ->

            if (photos.isNotEmpty()) {
                LazyGridFor(photos, 2) { roverPhoto ->
                    Card(
                        elevation = 4.dp,
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier.padding(8.dp)
                            .clickable(onClick = {
                                showImageDetails(roverPhoto)
                                Log.d("click", "ok")
                            })
                    ) {
                        val image =
                            loadPicture(url = roverPhoto.img_src).value
                        image?.let { img ->
                            Image(
                                bitmap = img.asImageBitmap(),
                                alignment = Alignment.Center,
                                modifier = Modifier
                                    .preferredHeight(250.dp)
                                    .fillMaxWidth(),
                                contentScale = ContentScale.Crop,
                            )

                        }
                    }
                }
            } else {
                Surface(
                    color = colorResource(id = R.color.grey),
                    modifier = Modifier.fillMaxWidth().fillMaxHeight()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(getString(R.string.no_image_found))
                    }
                }
            }
        }
    }

    private fun showImageDetails(roverPhoto: RoverPhoto) {
        mViewModel.showImageDetails(roverPhoto)
        imageDetailsDialog.show(supportFragmentManager, imageDetailsDialog.tag)
    }

    private fun showBottomSheet() {
        val filterOptionDialogFragment = FilterOptionBottomSheet()
        filterOptionDialogFragment.show(
            supportFragmentManager,
            filterOptionDialogFragment.tag
        )
    }
}