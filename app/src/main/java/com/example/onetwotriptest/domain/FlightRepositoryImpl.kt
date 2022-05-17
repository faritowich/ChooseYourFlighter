package com.example.onetwotriptest.domain

import com.example.onetwotriptest.data.network.FlightsApi
import javax.inject.Inject

class FlightRepositoryImpl @Inject constructor(private val api: FlightsApi) : FlightRepository {
    override suspend fun getCountries() = api.getFlights()
}