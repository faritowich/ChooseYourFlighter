package com.example.onetwotriptest.di

import com.example.onetwotriptest.data.network.FlightsApi
import com.example.onetwotriptest.domain.FlightRepository
import com.example.onetwotriptest.domain.FlightRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://603e34c648171b0017b2ec55.mockapi.io/ott/"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = okhttp3.logging.HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        okhttp3.OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): FlightsApi =
        retrofit.create(com.example.onetwotriptest.data.network.FlightsApi::class.java)

    @Singleton
    @Provides
    fun providesRepository(apiService: FlightsApi): FlightRepositoryImpl{
        return FlightRepositoryImpl(apiService)
    }
}