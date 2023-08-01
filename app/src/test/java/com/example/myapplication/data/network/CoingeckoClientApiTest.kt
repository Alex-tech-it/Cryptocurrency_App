package com.example.myapplication.data.network

import androidx.test.filters.MediumTest
import com.example.core.server.ServerConnectionConfig
import com.example.myapplication.data.state.ResultNetwork
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.Retrofit


@MediumTest
internal class CoingeckoClientApiTest {

    private lateinit var retrofit: Retrofit

    @BeforeEach
    fun setup() = runTest {
        retrofit = Retrofit.Builder().baseUrl(ServerConnectionConfig.BASE_URL).build()
    }

    @Test
    @DisplayName("check the size of the requested list")
    fun check_size_bounds_list() = runTest {
        val service = mock<CoingeckoApi>()
        whenever(service.marketCryptocurrency(size = "")).thenReturn(listOf())

        val coingeckoClientApiImpl = CoingeckoClientApiImpl(retrofit, service)
        val response = coingeckoClientApiImpl.loadCryptocurrencyList("-1")

        Assertions.assertEquals(ResultNetwork.Error, response)
    }

    @Test
    @DisplayName("verify success method call to the network")
    fun verify_success_method_call_network() = runTest {
        val service = mock<CoingeckoApi>()
        whenever(service.marketCryptocurrency(size = "10")).thenReturn(listOf())

        val coingeckoClientApiImpl = CoingeckoClientApiImpl(retrofit, service)
        val response = coingeckoClientApiImpl.loadCryptocurrencyList("10")

        verify(service, times(1)).marketCryptocurrency(size = "10")
        Assertions.assertEquals(true, response is ResultNetwork.Success)
    }

    @Test
    @DisplayName("check that an error has occurred when accessing the network")
    fun verify_error_occurred_network() = runTest {
        val service = mock<CoingeckoApi>()
        `when`(service.marketCryptocurrency(size = "10")).thenThrow()

        val coingeckoClientApiImpl = CoingeckoClientApiImpl(retrofit, service)
        val response = coingeckoClientApiImpl.loadCryptocurrencyList("10")

        Assertions.assertEquals(true, response is ResultNetwork.Error)
    }
}