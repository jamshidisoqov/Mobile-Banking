package uz.gita.mobile_banking.data.remote.request.transfer

import com.google.gson.annotations.SerializedName

// Created by Jamshid Isoqov on 12/22/2022
data class TransferDto(
    val type: String,
    @SerializedName(value = "sender-id")
    val senderId: String,
    @SerializedName(value = "receiver-pan")
    val receiverPan: String,
    val amount: Int
)
