package com.example.nit3213final.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nit3213final.data.model.Entity
import com.example.nit3213final.data.repository.DashboardRepository
import com.example.nit3213final.util.Result
import com.example.nit3213final.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repo: DashboardRepository
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<List<Entity>>>(UiState.Idle)
    val state: StateFlow<UiState<List<Entity>>> = _state

    fun load(keypass: String) {
        _state.value = UiState.Loading
        viewModelScope.launch {
            when (val result = repo.fetchEntities(keypass)) {
                is Result.Success -> _state.value = UiState.Success(result.data)
                is Result.Error -> _state.value = UiState.Error(result.message)
            }
        }
    }
}