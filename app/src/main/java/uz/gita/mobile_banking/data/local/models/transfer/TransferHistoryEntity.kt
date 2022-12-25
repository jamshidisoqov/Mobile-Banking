package uz.gita.mobile_banking.data.local.models.transfer

// Created by Jamshid Isoqov on 12/22/2022
data class TransferHistoryEntity(
    val totalElements: Int,
    val totalPages: Int,
    val currentPage: Int,
    val transfers: List<TransferEntity>
)
