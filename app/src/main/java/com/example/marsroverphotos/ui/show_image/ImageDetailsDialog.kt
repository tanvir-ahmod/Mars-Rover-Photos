package com.example.marsroverphotos.ui.show_image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.marsroverphotos.R
import com.example.marsroverphotos.models.RoverPhoto
import com.example.marsroverphotos.utills.loadPicture


class ImageDetailsDialog : DialogFragment() {

    private val sharedViewModel: ShowImageViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    ShowDetails()
                }
            }
        }
    }

    @Composable
    private fun ShowDetails() {
        val name: RoverPhoto? by sharedViewModel.showImageDetails.observeAsState()
        name?.let { roverPhoto ->
            Box {
                Column(modifier = Modifier.padding(16.dp)) {
                    val image =
                        loadPicture(url = roverPhoto.img_src).value
                    image?.let { img ->
                        Image(
                            bitmap = img.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(350.dp),
                            contentScale = ContentScale.Crop,
                        )
                        Row(modifier = Modifier.padding(top = 8.dp)) {
                            Text(
                                "Camera", modifier = Modifier.weight(1F),
                                fontSize = 18.sp,
                            )
                            Text(
                                roverPhoto.camera.full_name,
                                modifier = Modifier.weight(2F),
                                fontSize = 16.sp,
                                color = Color.Gray
                            )
                        }
                        Row(modifier = Modifier.padding(top = 8.dp)) {
                            Text(
                                "Earth Date", modifier = Modifier.weight(1F),
                                fontSize = 18.sp,
                            )
                            Text(
                                roverPhoto.earth_date,
                                modifier = Modifier.weight(2F),
                                fontSize = 16.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
                FloatingActionButton(
                    backgroundColor = Color.Red,
                    modifier = Modifier.padding(8.dp).align(Alignment.TopEnd).width(25.dp)
                        .height(25.dp),
                    onClick = {
                        dismiss()
                    }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_close),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.attributes?.windowAnimations = R.style.ImageDetailAnimation
    }
}