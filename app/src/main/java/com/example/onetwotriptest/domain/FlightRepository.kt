package com.example.onetwotriptest.domain

import com.example.onetwotriptest.data.network.FlightsApi


class FlightRepository(private val api: FlightsApi) : Repository {
    override suspend fun getCountries() = api.getFlights()
}