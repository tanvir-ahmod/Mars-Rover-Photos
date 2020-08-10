package com.example.marsroverimages.ui.show_image

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marsroverimages.R
import com.example.marsroverimages.base.ui.BaseActivity
import com.example.marsroverimages.models.RoverPhoto
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_show_image.*

@AndroidEntryPoint
class ShowImageActivity : BaseActivity<ShowImageViewModel>() {

     override val mViewModel: ShowImageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_image)

        val dummyRoverData: List<RoverPhoto> =
            arrayListOf(RoverPhoto(img_src = "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/ncam/NRB_486271176EDR_F0481570NCAM00322M_.JPG"));

        val roverImageAdapter = RoverImageAdapter(dummyRoverData)
        roverImageAdapter.communicator = object : RoverImageAdapter.Communicator {
            override fun clicked(roverPhoto: RoverPhoto) {
                val imageDetailsDialog = ImageDetailsDialog()
                imageDetailsDialog.show(supportFragmentManager, imageDetailsDialog.tag)
            }

        }
        rv_images.layoutManager = GridLayoutManager(this, 2)
        rv_images.adapter = roverImageAdapter
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