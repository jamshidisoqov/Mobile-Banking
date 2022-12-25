package uz.gita.mobile_banking.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.mobile_banking.data.remote.request.auth.*
import uz.gita.mobile_banking.data.remote.response.auth.HeaderData
import uz.gita.mobile_banking.data.remote.response.auth.TokenData
import uz.gita.mobile_banking.utils.ResultData

// Created by Jamshid Isoqov on 12/25/2022
interface AuthRepository {

    suspend fun register(registerDto: RegisterDto): Flow<ResultData<TokenData>>

    suspend fun login(loginDto: LoginDto): Flow<ResultData<TokenData>>

    suspend fun loginVerify(verifyDto: VerifyDto): Flow<ResultData<HeaderData>>

    suspend fun registerVerify(verifyDto: VerifyDto): Flow<ResultData<HeaderData>>

    suspend fun updateToken(updateTokenDto: UpdateTokenDto): Flow<ResultData<HeaderData>>

    suspend fun loginResendCode(tokenDto: TokenDto): Flow<ResultData<TokenData>>

    suspend fun registerResendCode(tokenDto: TokenDto): Flow<ResultData<TokenData>>

}