package com.example.myapplication.di.module

/**
 * @author Alex-tech-it
 * Github - https://github.com/Alex-tech-it
 */

import com.example.myapplication.data.network.CoingeckoClientApi
import com.example.myapplication.data.network.CoingeckoClientApiImpl
import com.example.myapplication.data.repository.CryptocurrencyRepositoryImpl
import com.example.myapplication.domain.repository.ICryptocurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit

@Module(includes = [DataModule.Binding::class])
@InstallIn(ViewModelComponent::class)
class DataModule {

    @Provides
    fun dispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideCoingeckoClientApi(
        retrofit: Retrofit
    ): CoingeckoClientApi {
        return CoingeckoClientApiImpl(retrofit)
    }

    @Module
    @InstallIn(ViewModelComponent::class)
    abstract class Binding {

        @Binds
        abstract fun bindCryptocurrencyRepoImpl(
            impl: CryptocurrencyRepositoryImpl
        ): ICryptocurrencyRepository

    }
}

