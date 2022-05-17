package com.example.onetwotriptest.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TypeAndAmount(
    @SerializedName("type")
    val type: String,
    @SerializedName("amount")
    val amount: Int
): Parcelable