package uz.gita.mobile_banking.data.remote.request.transfer

// Created by Jamshid Isoqov on 12/22/2022
data class TransferVerifyDto(
    val token: String,
    val code: String
)
