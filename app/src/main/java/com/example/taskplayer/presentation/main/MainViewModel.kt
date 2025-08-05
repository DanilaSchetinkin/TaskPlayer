package com.example.taskplayer.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskplayer.R
import com.example.taskplayer.data.provider.ResourceProvider
import com.example.taskplayer.domain.model.FeelingsResponse
import com.example.taskplayer.domain.model.QuotesResponse
import com.example.taskplayer.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: AuthRepository,
    private val  resources: ResourceProvider
) : ViewModel() {
    private val _feelings = MutableStateFlow<List<FeelingsResponse>>(emptyList())
    private val _quotes = MutableStateFlow<List<QuotesResponse>>(emptyList())

    val feelings: StateFlow<List<FeelingsResponse>> = _feelings
    val quotes: StateFlow<List<QuotesResponse>> = _quotes

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadData() {
        viewModelScope.launch {
            try {
                _feelings.value = repository.getFeelings()
                _quotes.value = repository.getQuotes()
            } catch (e: Exception){
                _error.value = resources.getString(R.string.Error_loading_data)
            }
        }
    }
}