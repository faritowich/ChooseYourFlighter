package com.example.onetwotriptest.repositories

import com.example.onetwotriptest.model.Flight
import retrofit2.Response

interface Repository {
    suspend fun getCountries(): Response<List<Flight>>
}