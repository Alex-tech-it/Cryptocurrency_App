package com.example.myapplication.data.model

import androidx.test.filters.SmallTest
import com.example.myapplication.domain.data.CryptocurrencyModel
import com.google.gson.Gson
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.File
import java.io.FileReader

@SmallTest
internal class NetworkCryptocurrencyModelTest {

    @Test
    @DisplayName("Check that the model serializes the Json object correctly")
    fun correct_serial_model() {
        val networkCryptocurrencyModel = getModelFromFileMock()
        Assertions.assertNotNull(networkCryptocurrencyModel)
    }

    @Test
    @DisplayName("Correct transformation of the network layer model into a domain layer model")
    fun convert_to_cryptocurrency_model() {
        val networkCryptocurrencyModel = getModelFromFileMock() ?: throw NullPointerException()
        val cryptocurrencyModel = networkCryptocurrencyModel.toCryptocurrencyModel()
        Assertions.assertEquals(
            cryptocurrencyModelMock,
            cryptocurrencyModel,
            "An error was made when converting to CryptocurrencyModel",
        )
    }

    private fun getModelFromFileMock(): NetworkCryptocurrencyModel? {
        val gson = Gson()
        return try {
            if (fileMock == null) return null
            gson.fromJson(FileReader(fileMock), NetworkCryptocurrencyModel::class.java)
        } catch (e: Throwable) {
            null
        }
    }

    companion object {

        private var fileMock: File? = null
        private lateinit var cryptocurrencyModelMock: CryptocurrencyModel

        @JvmStatic
        @BeforeAll
        fun setup(): Unit {
            fileMock = File(
                javaClass.getResource("/mock/mock_network_cryptocurrency_model.json")?.path
                    ?: "")
            cryptocurrencyModelMock = CryptocurrencyModel(
                id = "bitcoin", symbol = "btc", name = "Bitcoin",
                imageUrl = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
                currentPrice = "29152", marketCap = "566664350971",
                highPriceDay = "29310", lowPriceDay = "28983",
                priceChangeDay = "-157.09029249765445", priceChangePercentageDay = "-0.53597",
                lastUpdated = "2023-07-25T11:18:54.922Z"
            )
        }

        @JvmStatic
        @AfterAll
        fun destroy(): Unit {
            fileMock = null
        }
    }
}