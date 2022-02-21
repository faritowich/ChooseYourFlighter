package com.example.onetwotriptest.model

import com.google.gson.annotations.SerializedName

data class Flight(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("prices")
    val prices: List<TypeAndAmount>,
    @SerializedName("trips")
    val trips: List<FromAndTo>
)