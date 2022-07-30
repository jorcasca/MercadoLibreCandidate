package com.jorgecastanov.mercadolibrecandidate.ui.screens.feed

import com.jorgecastanov.mercadolibrecandidate.data.repository.FeedRepository
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class FeedViewModel(
    private val repository: FeedRepository
) : ViewModel() {

    val productIntent = Channel<FeedIntent>()
    private val _state = MutableStateFlow<FeedState>(FeedState.Idle)

    val state: StateFlow<FeedState>
        get() = _state

    init {
        handleIntents()
    }

    private fun handleIntents() {
        viewModelScope.launch {
            productIntent.consumeAsFlow().collect { intent ->
                when (intent) {
                    is FeedIntent.FetchProducts -> fetchProduct(intent.keyWord)
                }
            }
        }
    }

    private fun fetchProduct(keyWord: String) {
        viewModelScope.launch {
            _state.value = FeedState.Loading
            _state.value = try {
                FeedState.Products(repository.getProducts(keyWord))
            } catch (e: Exception) {
                FeedState.Error(e.localizedMessage)
            }
        }
    }
}