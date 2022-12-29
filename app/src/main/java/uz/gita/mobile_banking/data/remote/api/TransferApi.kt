package uz.gita.mobile_banking.data.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import uz.gita.mobile_banking.data.remote.request.transfer.*
import uz.gita.mobile_banking.data.remote.response.auth.TokenData
import uz.gita.mobile_banking.data.remote.response.transfer.PanData
import uz.gita.mobile_banking.data.remote.response.transfer.TransferFreeData
import uz.gita.mobile_banking.data.remote.response.transfer.TransferHistoryData
import uz.gita.mobile_banking.data.remote.response.user.MessageData

// Created by Jamshid Isoqov on 12/22/2022
interface TransferApi {


    @POST("transfer/card-owner")
    suspend fun addPan(@Body panDto: PanDto): Response<PanData>

    @POST("transfer/card-owner")
    suspend fun getCardOwnerByPan(@Body panDto: PanDto):Response<PanData>

    @POST("transfer/fee")
    suspend fun addFreeTransfer(@Body transferFreeDto: TransferFreeDto): Response<TransferFreeData>

    @POST("transfer/transfer")
    suspend fun addTransfer(@Body transferDto: TransferDto): Response<TokenData>

    @POST("transfer/transfer/verify")
    suspend fun transferVerify(@Body transferVerifyDto: TransferVerifyDto): Response<MessageData>

    @GET("transfer/history")
    suspend fun getTransfers(
        @Query("size") size: Int,
        @Query("current-page") currentPage: Int
    ): Response<TransferHistoryData>

    @POST("transfer/transfer/resend")
    suspend fun resendTransfer(@Body tokenDto: TokenDto): Response<TokenData>


}