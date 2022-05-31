package com.mohammed.khurram.digikraft.di

import com.mohammed.khurram.digikraft.remote.RemoteAPI
import com.mohammed.khurram.digikraft.utils.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class AppModules {

    /**
     * Hilt (Dagger) DI for retrofit object for remote data communication
     */
    @Provides
    fun provideRetrofitService(): RemoteAPI {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RemoteAPI::class.java)
    }

}