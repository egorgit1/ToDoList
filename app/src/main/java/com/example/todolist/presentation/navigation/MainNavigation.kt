package com.example.todolist.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.todolist.presentation.add.AddScreen
import com.example.todolist.presentation.edit.EditScreen
import com.example.todolist.presentation.feed.FeedScreen
import kotlinx.serialization.Serializable

sealed interface Screen {

    @Serializable
    data object Feed : Screen

    @Serializable
    data class Edit(val id: Int) : Screen

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
        composable<Screen.Edit> { backStackEntry ->
            val args = backStackEntry.toRoute<Screen.Edit>()
            EditScreen(id = args.id)
            { navigateTo ->
                navHostController.navigate(navigateTo)
            }

        }
    }
}
