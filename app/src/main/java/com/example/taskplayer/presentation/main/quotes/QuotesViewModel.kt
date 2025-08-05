package com.example.taskplayer.presentation.main.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskplayer.domain.model.QuotesResponse
import com.example.taskplayer.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuotesViewModel(private val repository: AuthRepository) : ViewModel() {
    private val _quotes = MutableStateFlow<List<QuotesResponse>>(emptyList())
    val quotes: StateFlow<List<QuotesResponse>> = _quotes

    fun loadQuotes() {
        viewModelScope.launch {
            try {
                val result = repository.getQuotes()
                _quotes.value = result
            } catch (e: Exception) {

            }
        }
    }
}