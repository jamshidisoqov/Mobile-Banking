package uz.gita.mobile_banking.data.remote.request.card

import com.google.gson.annotations.SerializedName

// Created by Jamshid Isoqov on 12/22/2022
data class UpdateCardDto(
    val id:Int,
    val name:String,
    @SerializedName("theme-type")
    val themeType:Int,
    @SerializedName(value = "is-visible")
    val isVisible: Boolean,
)
