package com.example.todolist.presentation.add

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
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
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        OutlinedTextField(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            value = state.title,
            onValueChange = { onEvent(AddScreenEvent.TitleChanged(it)) },
            placeholder = {
                Text(
                    text = stringResource(R.string.title),
                    fontSize = 18.sp
                )
            }
        )
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