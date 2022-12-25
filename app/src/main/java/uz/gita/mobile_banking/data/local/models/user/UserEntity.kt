package uz.gita.mobile_banking.data.local.models.user

// Created by Jamshid Isoqov on 12/22/2022
data class UserEntity(
    val phone: String,
    val firstName: String,
    val lastName: String,
    val bornDate: Long,
    val gender: Int
)
