package uz.gita.mobile_banking.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.mobile_banking.data.local.models.auth.HeaderTokenEntity
import uz.gita.mobile_banking.data.local.models.auth.TokenEntity
import uz.gita.mobile_banking.utils.ResultData

// Created by Jamshid Isoqov on 12/25/2022
interface AuthUseCase {

    fun login(phone: String, password: String): Flow<ResultData<TokenEntity>>

    fun register(
        phone: String,
        password: String,
        firstName: String,
        lastName: String,
        bornDate: Long,
        gender: Int
    ): Flow<ResultData<TokenEntity>>

    fun loginVerify(code: String): Flow<ResultData<HeaderTokenEntity>>

    fun registerVerify(code: String): Flow<ResultData<HeaderTokenEntity>>

    fun loginResendCode(): Flow<ResultData<TokenEntity>>

    fun registerResendCode(): Flow<ResultData<TokenEntity>>

}