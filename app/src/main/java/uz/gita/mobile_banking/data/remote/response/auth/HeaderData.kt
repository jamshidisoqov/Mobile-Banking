package uz.gita.mobile_banking.data.remote.response.auth

import com.google.gson.annotations.SerializedName

// Created by Jamshid Isoqov on 12/22/2022
data class HeaderData(
    @SerializedName(value = "refresh-token")
    val refreshToken: String,
    @SerializedName(value = "access-token")
    val accessToken: String
)
