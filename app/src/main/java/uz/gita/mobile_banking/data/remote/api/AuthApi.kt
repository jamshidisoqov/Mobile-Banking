package uz.gita.mobile_banking.data.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.gita.mobile_banking.data.remote.request.*
import uz.gita.mobile_banking.data.remote.request.auth.*
import uz.gita.mobile_banking.data.remote.response.auth.HeaderData
import uz.gita.mobile_banking.data.remote.response.auth.TokenData

// Created by Jamshid Isoqov on 12/22/2022
interface AuthApi {

    @POST("auth/sign-up")
    suspend fun register(@Body registerDto: RegisterDto): Response<TokenData>

    @POST("auth/sign-in")
    suspend fun login(@Body loginDto: LoginDto): Response<TokenData>

    @POST("auth/sign-in/verify")
    suspend fun loginVerify(@Body verifyDto: VerifyDto): Response<HeaderData>

    @POST("auth/sign-up/verify")
    suspend fun registerVerify(@Body verifyDto: VerifyDto): Response<HeaderData>

    @POST("auth/update-token")
    suspend fun updateToken(@Body updateTokenDto: UpdateTokenDto): Response<HeaderData>

    @POST("auth/sign-in/resend")
    suspend fun loginResendCode(tokenDto: TokenDto): Response<TokenData>

    @POST("auth/sign-up/resend")
    suspend fun registerResendCode(tokenDto: TokenDto): Response<TokenData>
}