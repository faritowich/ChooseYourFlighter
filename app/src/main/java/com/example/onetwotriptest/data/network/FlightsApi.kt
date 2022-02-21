package com.example.onetwotriptest.data.network

import com.example.onetwotriptest.model.Flight
import retrofit2.Response
import retrofit2.http.GET

interface FlightsApi {
    @GET("search")
    suspend fun getFlights(): Response<List<Flight>>
}