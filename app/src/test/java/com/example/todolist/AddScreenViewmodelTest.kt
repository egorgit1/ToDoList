package com.example.todolist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.todolist.domain.entity.Item
import com.example.todolist.domain.repository.ItemRepository
import com.example.todolist.presentation.add.AddScreenEvent
import com.example.todolist.presentation.add.AddScreenViewmodel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AddScreenViewmodelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)
    private lateinit var fakeItemRepository: FakeItemRepository
    private lateinit var viewModel: AddScreenViewmodel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        fakeItemRepository = FakeItemRepository()
        viewModel = AddScreenViewmodel(fakeItemRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state should have empty description, default color and not expanded`() = testScope.runTest {
        viewModel.state.test {
            val initialState = awaitItem()
            assert(initialState.description.isEmpty())
            assert(initialState.color == 0)
            assert(!initialState.isExpanded)
        }
    }

    @Test
    fun `onDescriptionChanged should update state description`() = testScope.runTest {
        val testDescription = "Test description"

        viewModel.state.test {
            awaitItem()

            viewModel.onEvent(AddScreenEvent.DescriptionChanged(testDescription))

            val updatedState = awaitItem()
            assert(updatedState.description == testDescription)
        }
    }

    @Test
    fun `onColorChanged should update state color`() = testScope.runTest {
        val testColor = 0xFF0000

        viewModel.state.test {
            awaitItem()

            viewModel.onEvent(AddScreenEvent.ColorChanged(testColor))

            val updatedState = awaitItem()
            assert(updatedState.color == testColor)
        }
    }

    @Test
    fun `onExpandChanged should update state isExpanded`() = testScope.runTest {
        viewModel.state.test {
            awaitItem()

            viewModel.onEvent(AddScreenEvent.ExpandChanged(true))

            val updatedState = awaitItem()
            assert(updatedState.isExpanded)
        }
    }

    @Test
    fun `onBtnClicked with empty description should not insert item`() = testScope.runTest {
        viewModel.onEvent(AddScreenEvent.ColorChanged(0xFF0000))
        advanceUntilIdle()

        viewModel.onEvent(AddScreenEvent.BackToFeedBtnClicked)
        advanceUntilIdle()

        assert(fakeItemRepository.insertedItems.isEmpty())
    }

    @Test
    fun `onBtnClicked with default color should not insert item`() = testScope.runTest {
        viewModel.onEvent(AddScreenEvent.DescriptionChanged("Test description"))
        advanceUntilIdle()

        viewModel.onEvent(AddScreenEvent.BackToFeedBtnClicked)
        advanceUntilIdle()

        assert(fakeItemRepository.insertedItems.isEmpty())
    }

    @Test
    fun `onBtnClicked with valid description and color should insert item`() = testScope.runTest {
        val testDescription = "Test description"
        val testColor = 0xFF0000

        viewModel.onEvent(AddScreenEvent.DescriptionChanged(testDescription))
        viewModel.onEvent(AddScreenEvent.ColorChanged(testColor))
        advanceUntilIdle()

        viewModel.onEvent(AddScreenEvent.BackToFeedBtnClicked)
        advanceUntilIdle()

        assert(fakeItemRepository.insertedItems.size == 1)
        assert(fakeItemRepository.insertedItems[0].description == testDescription)
        assert(fakeItemRepository.insertedItems[0].color == testColor)
    }

    @Test
    fun `onBtnClicked should save item with correct data`() = testScope.runTest {
        val expectedDescription = "Test item"
        val expectedColor = 123456

        viewModel.onEvent(AddScreenEvent.DescriptionChanged(expectedDescription))
        viewModel.onEvent(AddScreenEvent.ColorChanged(expectedColor))
        advanceUntilIdle()

        viewModel.onEvent(AddScreenEvent.BackToFeedBtnClicked)
        advanceUntilIdle()

        val savedItem = fakeItemRepository.insertedItems.first()
        assert(savedItem.description == expectedDescription)
        assert(savedItem.color == expectedColor)
    }

    @Test
    fun `multiple state updates should work correctly`() = testScope.runTest {
        viewModel.state.test {
            awaitItem()

            viewModel.onEvent(AddScreenEvent.DescriptionChanged("Description 1"))
            assert(awaitItem().description == "Description 1")

            viewModel.onEvent(AddScreenEvent.ColorChanged(123))
            val stateAfterColor = awaitItem()
            assert(stateAfterColor.description == "Description 1")
            assert(stateAfterColor.color == 123)

            viewModel.onEvent(AddScreenEvent.ExpandChanged(true))
            val stateAfterExpand = awaitItem()
            assert(stateAfterExpand.description == "Description 1")
            assert(stateAfterExpand.color == 123)
            assert(stateAfterExpand.isExpanded)
        }
    }
}

class FakeItemRepository : ItemRepository {
    val insertedItems = mutableListOf<TestItem>()

    override suspend fun insertItem(description: String, color: Int) {
        insertedItems.add(TestItem(description, color))
    }

    override suspend fun updateItemDescription(id: Int, description: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateItemColor(id: Int, color: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getItemById(id: Int): Item {
        TODO("Not yet implemented")
    }

    override suspend fun getItems(): List<Item> {
        TODO("Not yet implemented")
    }

    override suspend fun removeItemById(id: Int) {
        TODO("Not yet implemented")
    }
}

data class TestItem(
    val description: String,
    val color: Int
)