package com.example.onetwotriptest.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Flight(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("prices")
    val prices: List<TypeAndAmount>,
    @SerializedName("trips")
    val trips: List<FromAndTo>
) : Parcelable