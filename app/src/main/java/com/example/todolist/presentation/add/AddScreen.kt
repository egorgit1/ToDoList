package com.example.todolist.presentation.add

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todolist.R
import com.example.todolist.presentation.navigation.Screen

@Composable
fun AddScreen(
    navigate: (Screen) -> Unit
) {

    val viewmodel = hiltViewModel<AddScreenViewmodel>()
    val state by viewmodel.state.collectAsStateWithLifecycle()

    AddScreenContent(
        state,
        viewmodel::onEvent,
        navigate
    )
}

@Composable
fun AddScreenContent(
    state: AddScreenState,
    onEvent: (AddScreenEvent) -> Unit,
    navigate: (Screen) -> Unit
) {
    val mapOfColors = mapOf(
        stringResource(R.string.red) to R.color.red,
        stringResource(R.string.beige) to R.color.beige,
        stringResource(R.string.green) to R.color.green,
        stringResource(R.string.blue) to R.color.blue,
    )
    var selectedText by remember { mutableStateOf("Выбрать цвет") }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
        ) {
            TextField(
                value = selectedText,
                onValueChange = { },
                modifier = Modifier
                    .fillMaxWidth(),
                readOnly = true
            )
            IconButton(
                onClick = { onEvent(AddScreenEvent.ExpandChanged(true)) },
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(Icons.Default.ArrowDropDown, contentDescription = stringResource(R.string.choose_color))
            }
            DropdownMenu(
                expanded = state.isExpanded,
                onDismissRequest = { onEvent(AddScreenEvent.ExpandChanged(false)) }
            ) {
                mapOfColors.forEach { item ->
                    DropdownMenuItem(
                        onClick = {
                            selectedText = item.key
                            onEvent(AddScreenEvent.ExpandChanged(false))
                            onEvent(AddScreenEvent.ColorChanged(item.value))
                        },
                        text = {
                            Text(
                                text = item.key
                            )
                        }
                    )
                }
            }
        }
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        OutlinedTextField(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.9f),
            value = state.description,
            onValueChange = { onEvent(AddScreenEvent.DescriptionChanged(it)) },
            placeholder = {
                Text(
                    text = stringResource(R.string.description),
                    fontSize = 18.sp
                )
            }
        )
        BackHandler {
            onEvent(AddScreenEvent.BackToFeedBtnClicked)
            navigate(Screen.Feed)
        }
    }
}