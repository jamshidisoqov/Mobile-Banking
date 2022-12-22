package uz.gita.mobile_banking.data.remote.request.card

import com.google.gson.annotations.SerializedName

// Created by Jamshid Isoqov on 12/22/2022
data class CardDto(
    val pan: String,
    @SerializedName(value = "expired-month")
    val expiredMonth: String,
    @SerializedName(value = "expired-year")
    val expiredYear: String,
    val name: String

)
