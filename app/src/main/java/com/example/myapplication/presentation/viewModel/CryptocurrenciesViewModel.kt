package com.example.myapplication.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.myapplication.domain.useCase.CryptocurrencyItemsUseCase
import com.example.myapplication.presentation.paging.CryptocurrencyPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptocurrenciesViewModel @Inject constructor(
    private val cryptocurrencyItemsUseCase: CryptocurrencyItemsUseCase,
    private val cryptocurrenciesPaging: CryptocurrencyPagingSource
) : ViewModel(){

    /*
    *   Необходимо вынести создания Paging в Factory
    */
    val cryptocurrenciesFlow = Pager(PagingConfig(pageSize = 10)) {
        cryptocurrenciesPaging
    }.flow.cachedIn(viewModelScope)

    private val _isMessageShown = Channel<Boolean>(Channel.BUFFERED)
    val isMessageShownFlow = _isMessageShown.receiveAsFlow()

    fun showErrorLimitMessage() {
        viewModelScope.launch {
            _isMessageShown.send(true)
            _isMessageShown.cancel()
        }
    }
}