package com.example.todolist.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.R
import com.example.todolist.domain.entity.Item
import com.example.todolist.presentation.ui.theme.ToDoListTheme

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    item: Item,
    onItemClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .height(200.dp)
            .width(200.dp)
            .clickable {
                onItemClick()
            },
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.beige))
                .padding(10.dp)
        ) {
            Text(
                text = item.title,
                fontSize = 22.sp
            )
            Spacer(
                modifier = Modifier.height(10.dp)
            )
            Text(
                text = item.description,
                fontSize = 12.sp,
                maxLines = 3
            )
        }
    }
}

@Preview
@Composable
fun ItemPreview() {
    ToDoListTheme {
        ItemCard(
            item = Item(
                id = 1,
                title = "Title",
                description = "Description",
            ), onItemClick = {}
        )
    }
}