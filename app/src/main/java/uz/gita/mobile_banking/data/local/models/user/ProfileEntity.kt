package uz.gita.mobile_banking.data.local.models.user

// Created by Jamshid Isoqov on 12/22/2022
data class ProfileEntity(
    val firstName: String,
    val genderType: Int = 0,
    val age: Int
)
