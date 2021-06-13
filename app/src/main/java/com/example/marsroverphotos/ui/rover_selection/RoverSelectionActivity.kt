package com.example.marsroverphotos.ui.rover_selection

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marsroverphotos.R
import com.example.marsroverphotos.base.ui.BaseActivity
import com.example.marsroverphotos.databinding.ActivityRoverSelectionBinding
import com.example.marsroverphotos.models.QueryModel
import com.example.marsroverphotos.models.Rover
import com.example.marsroverphotos.ui.show_image.ShowImageActivity
import com.example.marsroverphotos.utills.Constants
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalPagerApi
class RoverSelectionActivity :
    BaseActivity<RoverSelectionViewModel, ActivityRoverSelectionBinding>() {

    companion object {
        const val QUERY_MODEL = "queryModel"
    }


    private lateinit var pagerState: PagerState

    override val mViewModel: RoverSelectionViewModel by viewModels()

    override fun getViewBinding(): ActivityRoverSelectionBinding =
        ActivityRoverSelectionBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val gotoNextActivity: QueryModel? by mViewModel.gotoNextActivity.observeAsState()
            gotoNextActivity?.let {
                goToNextActivity(it)
            }
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Column {
                    Text(
                        text = getString(R.string.select_a_rover),
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    )
                    ShowRovers()
                }

                FloatingActionButton(
                    backgroundColor = colorResource(id = R.color.colorAccent),
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.BottomCenter),
                    onClick = {
                        mViewModel.setSelectedRover(pagerState.currentPage)
                        mViewModel.goToNextActivity()
                    }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_check),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    }

    @Composable
    private fun ShowRovers() {
        val rovers: List<Rover>? by mViewModel.rovers.observeAsState()
        rovers?.let {
            pagerState = rememberPagerState(pageCount = it.size)
            Column {
                ShowIndicator(it.size - 1, pagerState.currentPage)
                HorizontalPager(state = pagerState) { page ->
                    val rover = it[page]
                    RoverBody(rover = rover)
                }
            }
        }
    }

    @Composable
    private fun ShowIndicator(count: Int, selected: Int) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
        ) {
            for (i in 0..count) {
                var color = colorResource(id = R.color.grey)
                if (i == selected) {
                    color = colorResource(id = R.color.colorAccent)
                }
                Spacer(modifier = Modifier.width(3.dp))
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(color)
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.width(3.dp))
            }
        }
    }

    @Composable
    private fun RoverBody(rover: Rover) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(rover.image),
                contentDescription = null,
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Fit,
            )
            Spacer(Modifier.height(16.dp))
            RoverInfoBody(getString(R.string.name), rover.name)
            RoverInfoBody(getString(R.string.launch_date), rover.launchDate)
            RoverInfoBody(getString(R.string.landing_date), rover.landingDate)

            var statusTextColor = Color.Red
            if (rover.currentStatus.equals(Constants.ACTIVE, ignoreCase = true))
                statusTextColor = colorResource(id = R.color.colorPrimary)
            RoverInfoBody(getString(R.string.status), rover.currentStatus, statusTextColor)
        }
    }

    @Composable
    private fun RoverInfoBody(item: String, value: String, valueTextColor: Color = Color.Gray) {
        Row(modifier = Modifier.padding(top = 8.dp)) {
            Text(
                item, modifier = Modifier.weight(1F),
                fontSize = 18.sp,
            )
            Text(
                value,
                modifier = Modifier.weight(1F),
                fontSize = 16.sp,
                color = valueTextColor
            )
        }
    }

    private fun goToNextActivity(queryModel: QueryModel) {
        val intent = Intent(this, ShowImageActivity::class.java)
        intent.putExtra(QUERY_MODEL, queryModel)
        startActivity(intent)
    }
}