package com.example.onetwotriptest.domain

import com.example.onetwotriptest.data.network.model.Flight
import retrofit2.Response

interface Repository {
    suspend fun getCountries(): Response<List<Flight>>
}