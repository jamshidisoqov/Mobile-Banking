package uz.gita.mobile_banking.data.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import uz.gita.mobile_banking.data.remote.request.user.UpdateUserDto
import uz.gita.mobile_banking.data.remote.response.user.*

// Created by Jamshid Isoqov on 12/22/2022
interface UserApi {

    @GET("home/total-balance")
    suspend fun getCurrentBalance(): Response<BalanceData>

    @GET("home/user-info")
    suspend fun getUserInfo(): Response<ProfileData>

    @GET("home/user-info/details")
    suspend fun getUserDetail(): Response<UserData>

    @PUT("home/user-info")
    suspend fun updateUser(@Body updateUserDto: UpdateUserDto): Response<MessageData>

    @GET("home/last-transfers")
    suspend fun getAllLastTransfers(): Response<List<LastTransferData>>
}