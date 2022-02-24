package com.example.onetwotriptest.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FromAndTo(
    @SerializedName("from")
    val from: String,
    @SerializedName("to")
    val to: String
) : Parcelable