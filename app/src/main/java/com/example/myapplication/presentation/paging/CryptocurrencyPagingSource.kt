package com.example.myapplication.presentation.paging

/**
 * @author Alex-tech-it
 * Github - https://github.com/Alex-tech-it
 */

import android.accounts.NetworkErrorException
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.domain.data.CryptocurrencyModel
import com.example.myapplication.domain.state.CryptocurrencyLoadedState
import com.example.myapplication.domain.useCase.CryptocurrencyItemsUseCase
import javax.inject.Inject

class CryptocurrencyPagingSource @Inject constructor(
    private val useCase: CryptocurrencyItemsUseCase
) : PagingSource<Int, CryptocurrencyModel>(){

    override fun getRefreshKey(state: PagingState<Int, CryptocurrencyModel>): Int? {
        val anchorPos = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPos) ?: return null
        return page.prevKey?.minus(1) ?: page.nextKey?.plus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CryptocurrencyModel> {
        val page: Int = params.key ?: 1
        val pageSize: Int = params.loadSize
        val response = useCase.execute(
            countItems = pageSize,
        )

        return try {
            when(response) {
                is CryptocurrencyLoadedState.SuccessLoad -> {
                    LoadResult.Page(response.data, null, page + 1)
                }
                is CryptocurrencyLoadedState.ErrorLoad -> {
                    LoadResult.Error(NetworkErrorException())
                }
            }
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }

}