package uz.gita.mobile_banking.data.remote.request.auth

import com.google.gson.annotations.SerializedName

data class LoginDto(
    @SerializedName(value = "phone")
    val password: String,
    @SerializedName(value = "password")
    val phone: String
)