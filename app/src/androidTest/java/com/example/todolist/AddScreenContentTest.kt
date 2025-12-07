package com.example.todolist.presentation.add

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.platform.LocalContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.todolist.presentation.navigation.Screen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddScreenContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setContent(
        state: AddScreenState = AddScreenState(),
        onEvent: (AddScreenEvent) -> Unit = {},
        navigate: (Screen) -> Unit = {}
    ) {
        composeTestRule.setContent {
            AddScreenContent(
                state = state,
                onEvent = onEvent,
                navigate = navigate
            )
        }
    }

    @Test
    fun expandButton_opensDropdownMenu() {
        var updatedExpanded = false

        setContent(
            state = AddScreenState(isExpanded = false),
            onEvent = {
                if (it is AddScreenEvent.ExpandChanged) {
                    updatedExpanded = it.newExpand
                }
            }
        )

        composeTestRule
            .onNodeWithTag("expand_button")
            .performClick()

        assert(updatedExpanded)
    }

    @Test
    fun dropdown_showsItems_afterExpand() {
        setContent(
            state = AddScreenState(isExpanded = true)
        )

        composeTestRule
            .onNodeWithText("Красный", substring = true)
            .assertExists()
    }

    @Test
    fun dropdown_selectsColor_andCloses() {
        var selectedColor: Int? = null
        var closed = false

        setContent(
            state = AddScreenState(isExpanded = true),
            onEvent = {
                when (it) {
                    is AddScreenEvent.ColorChanged -> selectedColor = it.newColor
                    is AddScreenEvent.ExpandChanged -> if (!it.newExpand) closed = true
                    else -> {}
                }
            }
        )

        composeTestRule
            .onNodeWithText("Красный", substring = true)
            .performClick()

        assert(selectedColor != null)
        assert(closed)
    }

    @Test
    fun descriptionField_acceptsInput() {
        var desc = ""

        setContent(
            onEvent = {
                if (it is AddScreenEvent.DescriptionChanged) {
                    desc = it.newDescription
                }
            }
        )

        composeTestRule
            .onNodeWithTag("description_field")
            .performTextInput("Hello world")

        assert(desc == "Hello world")
    }

}
