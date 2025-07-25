package com.example.taskplayer.screen.MainScreen.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskplayer.data.remote.auth.QuotesResponse
import com.example.taskplayer.data.remote.repository.AuthRepository
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