package com.example.onetwotriptest.model

import com.google.gson.annotations.SerializedName

data class TypeAndAmount(
    @SerializedName("type")
    val type: String,
    @SerializedName("amount")
    val amount: Int
)