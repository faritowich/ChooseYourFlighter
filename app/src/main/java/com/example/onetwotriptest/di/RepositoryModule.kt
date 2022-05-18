package com.example.onetwotriptest.di

import com.example.onetwotriptest.domain.FlightRepository
import com.example.onetwotriptest.domain.FlightRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun FlightRepositoryImpl.bindRepo(): FlightRepository
}