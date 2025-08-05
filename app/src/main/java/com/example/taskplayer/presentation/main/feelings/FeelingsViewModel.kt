package com.example.taskplayer.presentation.main.feelings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskplayer.domain.model.FeelingsResponse
import com.example.taskplayer.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FeelingsViewModel(private val repository: AuthRepository) : ViewModel() {
    private val _feelings = MutableStateFlow<List<FeelingsResponse>>(emptyList())
    val feelings: StateFlow<List<FeelingsResponse>> = _feelings

    fun loadFeelings() {
        viewModelScope.launch {
            Log.d("FeelingsViewModel", "loadFeelings() called")
            try {
                val result = repository.getFeelings()
                Log.d("FeelingsViewModel", "Loaded feelings: ${result.size}")
                _feelings.value = result
            } catch (e: Exception) {
                Log.e("FeelingsViewModel", "Error loading feelings", e)
            }
        }
    }
}

