package com.example.nit3213final.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nit3213final.BuildConfig
import com.example.nit3213final.data.remote.ApiService
import com.example.nit3213final.data.remote.dto.LoginRequest
import com.example.nit3213final.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val api: ApiService
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<String>>(UiState.Idle)
    val state: StateFlow<UiState<String>> = _state

    fun login(username: String, password: String) {
        _state.value = UiState.Loading

        viewModelScope.launch {
            try {
                // ✅ Use @Url to ensure the path is exactly what the API expects
                val fullUrl = "${BuildConfig.BASE_URL}${BuildConfig.AUTH_PATH}"
                val response = api.login(fullUrl, LoginRequest(username, password))
                val keypass = response.keypass

                if (keypass.isNullOrBlank()) {
                    _state.value = UiState.Error("Login succeeded but keypass missing")
                } else {
                    _state.value = UiState.Success(keypass)
                }

            } catch (e: HttpException) {
                _state.value = UiState.Error("Login failed (${e.code()}). Check endpoint + credentials.")
            } catch (e: IOException) {
                _state.value = UiState.Error("Network error. Check internet or if server is starting up.")
            } catch (e: Exception) {
                _state.value = UiState.Error("Unexpected error: ${e.message}")
            }
        }
    }
}
