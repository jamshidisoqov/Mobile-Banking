package uz.gita.mobile_banking.data.remote.request.auth

import com.google.gson.annotations.SerializedName

// Created by Jamshid Isoqov on 12/22/2022
data class UpdateTokenDto(
    @SerializedName(value = "refresh-token")
    val refreshToken: String
)
