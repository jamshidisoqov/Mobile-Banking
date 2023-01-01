package uz.gita.mobile_banking.data.remote.request.auth

import com.google.gson.annotations.SerializedName

data class LoginDto(
    @SerializedName(value = "password")
    val password: String,
    @SerializedName(value = "phone")
    val phone: String
)