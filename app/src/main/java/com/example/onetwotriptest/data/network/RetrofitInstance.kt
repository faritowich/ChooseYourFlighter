package com.example.onetwotriptest.data.network

import com.example.onetwotriptest.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//object RetrofitInstance {
//    private val retrofit by lazy {
//        Retrofit.Builder()
//            .baseUrl(Constants.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    val api: FlightsApi by lazy {
//        retrofit.create(FlightsApi::class.java)
//    }
//}