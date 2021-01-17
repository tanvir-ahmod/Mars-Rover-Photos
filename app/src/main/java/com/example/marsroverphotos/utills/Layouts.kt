package com.example.marsroverphotos.utills

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ColumnSpacer(value: Int) = Spacer(modifier = Modifier.preferredHeight(value.dp))

@Composable
fun GridItem(thumbnail: String, title: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(
            elevation = 4.dp,
            modifier = Modifier.clickable { onClick() },
            shape = MaterialTheme.shapes.medium
        ) {

            val padding = Modifier.padding(horizontal = 8.dp)
            Text(
                text = "ok",
                style = MaterialTheme.typography.body2,
                modifier = padding
            )
            /*if (desc.isNotEmpty()) {
                ColumnSpacer(4)
                Text(
                    text = desc,
                    style = MaterialTheme.typography.overline,
                    modifier = padding
                )
            }*/
//            Spacer(Modifier.preferredHeight(8.dp))
        }
    }
}

/*@Composable
fun <T> LazyGridFor(
    items: List<T> = listOf(),
    rows: Int = 3,
    hPadding: Int = 8,
    itemContent: @Composable LazyItemScope.(T, Int) -> Unit
) {
    val animatedSet = remember { mutableSetOf<Int>() }
    val chunkedList = items.chunked(rows)
    LazyColumn(modifier = Modifier.padding(horizontal = hPadding.dp)) {
        itemsIndexed(items = chunkedList, itemContent = { index, it ->
            if (index == 0) {
                ColumnSpacer(value = 8)
            }
            val offsetValue = animatedFloat(initVal = if (index in animatedSet) 0F else 150F)
            val alphaValue = animatedFloat(initVal = if (index in animatedSet) 1F else 0F)

            Row(
                modifier = Modifier.offset(y = offsetValue.value.toInt().dp)
                    .alpha(alphaValue.value)
            ) {
                it.forEachIndexed { rowIndex, item ->
                    Box(
                        modifier = Modifier.weight(1F).align(Alignment.Top).padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        itemContent(item, index * rows + rowIndex)
                    }
                }
                repeat(rows - it.size) {
                    Box(modifier = Modifier.weight(1F).padding(8.dp)) {}
                }
            }
        })
    }
}*/

@Composable
fun <T> LazyGridFor(
    items: List<T>,
    rowSize: Int = 1,
    itemContent: @Composable BoxScope.(T) -> Unit,
) {
    val rows = items.chunked(rowSize)
    LazyColumnFor(rows) { row ->
        Row(Modifier.fillParentMaxWidth()) {
            for ((index, item) in row.withIndex()) {
                Box(Modifier.fillMaxWidth(1f / (rowSize - index))) {
                    itemContent(item)
                }
            }
        }
    }
}

