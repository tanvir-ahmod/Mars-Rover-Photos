package com.example.marsroverimages.ui.show_image

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marsroverimages.R
import com.example.marsroverimages.base.ui.BaseActivity
import com.example.marsroverimages.models.RoverData
import com.example.marsroverimages.models.RoverPhoto
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_show_image.*

@AndroidEntryPoint
class ShowImageActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_image)

        val dummyRoverData: List<RoverPhoto> =
            arrayListOf(RoverPhoto(img_src = "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/ncam/NRB_486271176EDR_F0481570NCAM00322M_.JPG"));

        val roverImageAdapter = RoverImageAdapter(dummyRoverData)
        rv_images.layoutManager = GridLayoutManager(this, 2)
        rv_images.adapter = roverImageAdapter
    }
}