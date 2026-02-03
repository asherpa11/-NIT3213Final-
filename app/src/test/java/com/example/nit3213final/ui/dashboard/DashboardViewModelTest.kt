package com.example.nit3213final.ui.dashboard

import app.cash.turbine.test
import com.example.nit3213final.data.model.Entity
import com.example.nit3213final.data.repository.DashboardRepository
import com.example.nit3213final.util.Result
import com.example.nit3213final.util.UiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardViewModelTest {

    private val repo: DashboardRepository = mockk()
    private lateinit var viewModel: DashboardViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = DashboardViewModel(repo)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `load success sets Success state with entities`() = runTest {
        val entities = listOf(Entity("1", emptyMap(), "desc"))
        coEvery { repo.fetchEntities("test_key") } returns Result.Success(entities)

        viewModel.load("test_key")

        viewModel.state.test {
            assertEquals(UiState.Loading, awaitItem())
            assertEquals(UiState.Success(entities), awaitItem())
        }
    }

    @Test
    fun `load failure sets Error state`() = runTest {
        coEvery { repo.fetchEntities("test_key") } returns Result.Error("Error")

        viewModel.load("test_key")

        viewModel.state.test {
            assertEquals(UiState.Loading, awaitItem())
            assertEquals(UiState.Error("Error"), awaitItem())
        }
    }
}
