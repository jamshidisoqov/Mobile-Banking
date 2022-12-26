package uz.gita.mobile_banking.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.mobile_banking.data.remote.request.auth.*
import uz.gita.mobile_banking.data.remote.response.auth.HeaderData
import uz.gita.mobile_banking.data.remote.response.auth.TokenData
import uz.gita.mobile_banking.utils.ResultData

// Created by Jamshid Isoqov on 12/25/2022
interface AuthRepository {

    fun register(registerDto: RegisterDto): Flow<ResultData<TokenData>>

    fun login(loginDto: LoginDto): Flow<ResultData<TokenData>>

    fun loginVerify(verifyDto: VerifyDto): Flow<ResultData<HeaderData>>

    fun registerVerify(verifyDto: VerifyDto): Flow<ResultData<HeaderData>>

    fun updateToken(updateTokenDto: UpdateTokenDto): Flow<ResultData<HeaderData>>

    fun loginResendCode(tokenDto: TokenDto): Flow<ResultData<TokenData>>

    fun registerResendCode(tokenDto: TokenDto): Flow<ResultData<TokenData>>

}