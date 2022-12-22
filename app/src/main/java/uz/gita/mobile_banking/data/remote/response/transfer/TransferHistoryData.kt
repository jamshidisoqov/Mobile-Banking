package uz.gita.mobile_banking.data.remote.response.transfer

import com.google.gson.annotations.SerializedName

// Created by Jamshid Isoqov on 12/22/2022
data class TransferHistoryData(
    @SerializedName("total-elements")
    val totalElements: Int,
    @SerializedName("total-pages")
    val totalPages: Int,
    @SerializedName("current-page")
    val currentPage: Int,
    @SerializedName("child")
    val transfers: List<TransferData>
)
