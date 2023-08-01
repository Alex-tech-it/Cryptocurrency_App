package com.example.myapplication.domain.useCase


import androidx.test.filters.MediumTest
import com.example.myapplication.domain.data.CryptocurrencyModel
import com.example.myapplication.domain.repository.ICryptocurrencyRepository
import com.example.myapplication.domain.state.CryptocurrencyLoadedState
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

const val testCountItems = 10

@MediumTest
internal class CryptocurrencyItemsUseCaseTest {

    private lateinit var mockData: List<CryptocurrencyModel>

    @BeforeEach
    fun setup() {
        mockData = (1..5).map { CryptocurrencyModel() }
    }

    @Test
    fun execute_use_case_correctly() = runTest {
        val repoMock = cryptocurrencyRepository(isWrong = false)
        val useCase = CryptocurrencyItemsUseCase(repoMock)

        val resultExecute = useCase.execute(countItems = testCountItems)

        verify(repoMock, times(1)).cryptocurrencyList(testCountItems)
        Assertions.assertEquals(true, resultExecute is CryptocurrencyLoadedState.SuccessLoad)
        Assertions.assertArrayEquals(
            mockData.toTypedArray(),
            (resultExecute as CryptocurrencyLoadedState.SuccessLoad).data.toTypedArray()
        )
    }

    @Test
    fun use_case_get_wrong_or_empty_data() = runTest {
        val repoMock = cryptocurrencyRepository(isWrong = true)
        val useCase = CryptocurrencyItemsUseCase(repoMock)

        val resultExecute = useCase.execute(countItems = testCountItems)

        verify(repoMock, times(1)).cryptocurrencyList(testCountItems)
        Assertions.assertEquals(true, resultExecute is CryptocurrencyLoadedState.ErrorLoad)
    }

    private suspend fun cryptocurrencyRepository(isWrong: Boolean): ICryptocurrencyRepository {
        val mockRepo = mock<ICryptocurrencyRepository>()
        whenever(mockRepo.cryptocurrencyList(anyInt())).thenReturn(
            if (isWrong) listOf() else mockData
        )
        return mockRepo
    }
}