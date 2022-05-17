package com.example.onetwotriptest.domain

import com.example.onetwotriptest.data.model.Flight
import retrofit2.Response

interface FlightRepository {
    suspend fun getCountries(): Response<List<Flight>>
}