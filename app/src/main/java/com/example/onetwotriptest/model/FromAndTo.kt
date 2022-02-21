package com.example.onetwotriptest.model

import com.google.gson.annotations.SerializedName

data class FromAndTo(
    @SerializedName("from")
    val from: String,
    @SerializedName("to")
    val to: String
)