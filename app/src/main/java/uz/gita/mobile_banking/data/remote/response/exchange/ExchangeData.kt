package uz.gita.mobile_banking.data.remote.response.exchange

import com.google.gson.annotations.SerializedName

data class ExchangeData(
    @SerializedName("Ccy")
    val exchangeShortName: String,
    @SerializedName("CcyNm_EN")
    val exchangeEngName: String,
    @SerializedName("CcyNm_RU")
    val exchangeRuName: String,
    @SerializedName("CcyNm_UZ")
    val exchangeUzName: String,
    @SerializedName("CcyNm_UZC")
    val exchangeKirName: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("Date")
    val date: String,
    @SerializedName("Diff")
    val status: String,
    @SerializedName("Nominal")
    val nominal: String,
    @SerializedName("Rate")
    val rate: String,
    val id: Int
)