package uz.gita.mobile_banking.data.remote.request.user

import com.google.gson.annotations.SerializedName

// Created by Jamshid Isoqov on 12/22/2022
data class UpdateUserDto(
    @SerializedName(value = "first-name")
    val firstName: String,
    @SerializedName(value = "last-name")
    val lastName: String,
    @SerializedName(value = "born-date")
    val bornDate: Long,
    @SerializedName(value = "gender-type")
    val gender: Int
)