package uz.gita.mobile_banking.data.remote.request.transfer

import com.google.gson.annotations.SerializedName

// Created by Jamshid Isoqov on 12/22/2022
data class TransferFreeDto(
    @SerializedName("sender-id")
    val senderId: String,
    val receiver: String,
    val amount: Int
)
