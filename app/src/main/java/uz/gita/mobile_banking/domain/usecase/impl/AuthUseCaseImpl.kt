package uz.gita.mobile_banking.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.mobile_banking.data.local.models.auth.HeaderTokenEntity
import uz.gita.mobile_banking.data.local.models.auth.TokenEntity
import uz.gita.mobile_banking.domain.usecase.AuthUseCase
import uz.gita.mobile_banking.utils.ResultData

class AuthUseCaseImpl : AuthUseCase {
    override fun login(phone: String, password: String): Flow<ResultData<TokenEntity>> {
        TODO("Not yet implemented")
    }

    override fun register(
        phone: String,
        password: String,
        firstName: String,
        lastName: String,
        bornDate: Long,
        gender: Int
    ): Flow<ResultData<TokenEntity>> {
        TODO("Not yet implemented")
    }

    override fun loginVerify(code: String): Flow<ResultData<HeaderTokenEntity>> {
        TODO("Not yet implemented")
    }

    override fun registerVerify(code: String): Flow<ResultData<HeaderTokenEntity>> {
        TODO("Not yet implemented")
    }

    override fun loginResendCode(): Flow<ResultData<TokenEntity>> {
        TODO("Not yet implemented")
    }

    override fun registerResendCode(): Flow<ResultData<TokenEntity>> {
        TODO("Not yet implemented")
    }
}