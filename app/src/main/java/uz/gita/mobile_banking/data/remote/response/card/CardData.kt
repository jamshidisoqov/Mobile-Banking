package uz.gita.mobile_banking.data.remote.response.card

import com.google.gson.annotations.SerializedName

data class CardData(
    val id: Int,
    val amount: Int,
    @SerializedName(value = "expired-month")
    val expiredMonth: Int,
    @SerializedName(value = "expired-year")
    val expiredYear: Int,
    @SerializedName(value = "is-visible")
    val isVisible: Boolean,
    val name: String,
    val owner: String,
    val pan: String,
    @SerializedName(value = "theme-type")
    val themeType: Int
)