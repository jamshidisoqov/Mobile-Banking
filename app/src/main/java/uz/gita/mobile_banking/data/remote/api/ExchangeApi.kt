package uz.gita.mobile_banking.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import uz.gita.mobile_banking.data.remote.response.exchange.ExchangeData

// Created by Jamshid Isoqov on 1/1/2023
interface ExchangeApi {

    @GET("/arkhiv-kursov-valyut/json/")
    suspend fun getAllCurrency(): Response<List<ExchangeData>>

}