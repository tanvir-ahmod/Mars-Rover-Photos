package com.example.marsroverphotos.utills.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.marsroverphotos.R

@Composable
fun ImageFilterChip(text: String, hasAction: Boolean = false, onClick: () -> Unit) {
    Surface(
        modifier =  Modifier.padding(8.dp),
        color = colorResource(id = R.color.grey),
        elevation = 8.dp,
        shape = RoundedCornerShape(40),
    ) {
        Row (modifier = Modifier.padding(8.dp)){
            Text(text)
            if (hasAction)
                IconButton(modifier = Modifier.then(Modifier.preferredSize(16.dp)),
                    onClick = { onClick() }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                    )
                }
        }
    }
}