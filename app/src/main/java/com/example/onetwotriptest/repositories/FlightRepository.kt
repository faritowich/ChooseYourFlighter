package com.example.onetwotriptest.repositories

import com.example.onetwotriptest.data.network.FlightsApi
import com.example.onetwotriptest.data.network.RetrofitInstance
import com.example.onetwotriptest.model.Flight
import retrofit2.Response


class FlightRepository(private val api: FlightsApi) {

//    override suspend fun getPokemonResponseFromServer(): Response<Flight> {
//        return RetrofitInstance.api.getFlights()
//    }

    suspend fun getCountries() = api.getFlights()
}