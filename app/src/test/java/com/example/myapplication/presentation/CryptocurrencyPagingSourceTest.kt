package com.example.myapplication.presentation

import android.accounts.NetworkErrorException
import androidx.paging.PagingSource
import com.example.myapplication.domain.data.CryptocurrencyModel
import com.example.myapplication.domain.repository.ICryptocurrencyRepository
import com.example.myapplication.domain.useCase.CryptocurrencyItemsUseCase
import com.example.myapplication.presentation.paging.CryptocurrencyPagingSource
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.lang.NullPointerException
import java.lang.RuntimeException

internal class CryptocurrencyPagingSourceTest {

    private lateinit var cryptocurrencyRepo: ICryptocurrencyRepository
    private lateinit var cryptocurrencyPaging: CryptocurrencyPagingSource

    @BeforeEach
    fun setup() {
        cryptocurrencyRepo = mock()
        cryptocurrencyPaging = CryptocurrencyPagingSource(CryptocurrencyItemsUseCase(cryptocurrencyRepo))
    }

    @Test
    @DisplayName("cryptocurrencies paging source load - failure - http error")
    fun paging_source_load_failure_network() = runTest {

        given(cryptocurrencyRepo.cryptocurrencyList(any())).willReturn(listOf())

        val expectedResult = PagingSource.LoadResult.Error<Int, CryptocurrencyModel>(NetworkErrorException())
        val execute = cryptocurrencyPaging.load(
            PagingSource.LoadParams.Refresh(
                key = 0,
                loadSize = 5,
                placeholdersEnabled = false
            )
        )
        Assertions.assertEquals(
            expectedResult.throwable.message,
            (execute as PagingSource.LoadResult.Error<Int, CryptocurrencyModel>).throwable.message
        )
    }

    @Test
    @DisplayName("cryptocurrencies paging source load - success")
    fun paging_source_load_success() = runTest {

        given(cryptocurrencyRepo.cryptocurrencyList(any())).willReturn(responseData)

        val expectedResult = PagingSource.LoadResult.Page<Int, CryptocurrencyModel>(
            data = responseData,
            prevKey = null,
            nextKey = 1
        )

        val execute = cryptocurrencyPaging.load(
            PagingSource.LoadParams.Refresh(
                key = 0,
                loadSize = 10,
                placeholdersEnabled = false
            )
        )
        Assertions.assertArrayEquals(
            expectedResult.data.toTypedArray(),
            (execute as PagingSource.LoadResult.Page<Int, CryptocurrencyModel>).data.toTypedArray()
        )
    }


    companion object {

        private var responseData = (1..10).mapIndexed { _, value ->
            CryptocurrencyModel(id = "$value")
        }
    }
}