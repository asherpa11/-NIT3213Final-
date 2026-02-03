package com.example.nit3213final.ui.login

import app.cash.turbine.test
import com.example.nit3213final.data.remote.ApiService
import com.example.nit3213final.data.remote.dto.LoginResponse
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
class LoginViewModelTest {

    private val api: ApiService = mockk()
    private lateinit var viewModel: LoginViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = LoginViewModel(api)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `login success sets Success state with keypass`() = runTest {
        val expectedKeypass = "test_key"
        // ✅ Fixed: Match both parameters of the updated login function
        coEvery { api.login(any(), any()) } returns LoginResponse(expectedKeypass)

        viewModel.login("user", "pass")

        viewModel.state.test {
            assertEquals(UiState.Loading, awaitItem())
            assertEquals(UiState.Success(expectedKeypass), awaitItem())
        }
    }

    @Test
    fun `login failure sets Error state`() = runTest {
        // ✅ Fixed: Match both parameters of the updated login function
        coEvery { api.login(any(), any()) } throws Exception("Network Error")

        viewModel.login("user", "pass")

        viewModel.state.test {
            assertEquals(UiState.Loading, awaitItem())
            val error = awaitItem() as UiState.Error
            assertEquals("Unexpected error: Network Error", error.message)
        }
    }
}
