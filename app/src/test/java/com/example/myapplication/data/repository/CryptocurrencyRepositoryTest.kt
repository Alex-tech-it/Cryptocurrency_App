package com.example.myapplication.data.repository

import androidx.test.filters.MediumTest
import com.example.myapplication.data.model.NetworkCryptocurrencyModel
import com.example.myapplication.data.model.toCryptocurrencyModel
import com.example.myapplication.data.network.CoingeckoClientApi
import com.example.myapplication.data.state.ResultNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@MediumTest
internal class CryptocurrencyRepositoryTest {

    private lateinit var mockRequestData: List<NetworkCryptocurrencyModel>

    @BeforeEach
    fun setup() {
        mockRequestData = (1..10).mapIndexed { index, _ ->
            NetworkCryptocurrencyModel(id = "$index")
        }
    }

    @Test
    @DisplayName("changing the saving of the number of loaded items (if is first time)")
    fun changing_current_loaded_items_first_time() = runTest {
        val countItems = 10

        val mockApi = getMockClientApi()
        val repository = CryptocurrencyRepositoryImpl(mockApi, Dispatchers.Default)
        repository.cryptocurrencyList(countItems)

        verify(mockApi).loadCryptocurrencyList(size = countItems.toString())
        Assertions.assertEquals(countItems, repository.currentLoadedItems)
    }

    @Test
    @DisplayName("changing the saving of the number of loaded items (several times)")
    fun changing_current_loaded_items_several_times() = runTest {
        val countItems = 5
        val resultLoadedItems = countItems * 3

        val mockApi = getMockClientApi()
        val repository = CryptocurrencyRepositoryImpl(mockApi, Dispatchers.Default)

        repository.cryptocurrencyList(countItems)
        repository.cryptocurrencyList(countItems)
        repository.cryptocurrencyList(countItems)

        verify(mockApi).loadCryptocurrencyList(size = countItems.toString())
        Assertions.assertEquals(resultLoadedItems, repository.currentLoadedItems)
    }


    @Test
    @DisplayName("check that returned list is valid")
    fun check_valid_result_return() = runTest {
        val countItems = 10
        val expectedList = mockRequestData.map { it.toCryptocurrencyModel() }
        val mockApi = getMockClientApi()
        val repository = CryptocurrencyRepositoryImpl(mockApi, Dispatchers.Default)

        val resultList = repository.cryptocurrencyList(countItems)

        Assertions.assertArrayEquals(expectedList.toTypedArray(), resultList.toTypedArray())
    }

    @Test
    @DisplayName("test is counts items less 0")
    fun check_counts_item_less_zero() = runTest {
        val countItems = -1
        val mockApi = getMockClientApi(requestReturn = true)
        val repository = CryptocurrencyRepositoryImpl(mockApi, Dispatchers.Default)

        val resultWork = repository.cryptocurrencyList(countItems)

        Assertions.assertEquals(0, resultWork.size)
    }

    @Test
    @DisplayName("network malfunction check")
    fun check_api_error_request() = runTest {
        val mockApi = getMockClientApi(requestReturn = false)
        val repository = CryptocurrencyRepositoryImpl(mockApi, Dispatchers.Default)

        val resultWork = repository.cryptocurrencyList(10)

        Assertions.assertEquals(0, resultWork.size)
    }

    private suspend fun getMockClientApi(requestReturn: Boolean = true): CoingeckoClientApi {
        val mockApiClient = mock<CoingeckoClientApi>()
        whenever(mockApiClient.loadCryptocurrencyList(anyString())).thenReturn(
            if (requestReturn) ResultNetwork.Success(mockRequestData) else ResultNetwork.Error
        )
        return mockApiClient
    }
}