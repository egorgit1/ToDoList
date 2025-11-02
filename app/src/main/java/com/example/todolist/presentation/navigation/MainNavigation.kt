package com.example.todolist.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todolist.domain.entity.Item
import com.example.todolist.presentation.add.AddScreen
import com.example.todolist.presentation.edit.EditScreen
import com.example.todolist.presentation.feed.FeedScreen
import kotlinx.serialization.Serializable

sealed interface Screen {

    @Serializable
    data object Feed : Screen

    @Serializable
    data object Edit : Screen

    @Serializable
    data object Add : Screen
}

@Composable
fun MainNav(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = Screen.Feed
    ) {
        composable<Screen.Feed> {
            FeedScreen { navigateTo ->
                navHostController.navigate(navigateTo)
            }
        }
        composable<Screen.Add> {
            AddScreen { navigateTo ->
                navHostController.navigate(navigateTo)
            }
        }
        composable<Screen.Edit> {
            val item = navHostController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<java.io.Serializable>("item") as? Item

            EditScreen(item = item!!) { navigateTo ->
                navHostController.navigate(navigateTo)
            }
        }
    }
}
