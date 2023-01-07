package uz.gita.mobile_banking.domain.repository.impl

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.mobile_banking.data.remote.api.UserApi
import uz.gita.mobile_banking.data.remote.request.user.UpdateUserDto
import uz.gita.mobile_banking.data.remote.response.user.*
import uz.gita.mobile_banking.domain.repository.UserRepository
import uz.gita.mobile_banking.utils.ResultData
import uz.gita.mobile_banking.utils.func
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi,
    private val gson: Gson
) : UserRepository {
    override fun getCurrentBalance(): Flow<ResultData<BalanceData>> = flow {
        emit(userApi.getCurrentBalance().func(gson = gson))
    }.flowOn(Dispatchers.IO)

    override fun getUserInfo(): Flow<ResultData<ProfileData>> = flow {
        emit(userApi.getUserInfo().func(gson))
    }.flowOn(Dispatchers.IO)

    override fun getUserDetail(): Flow<ResultData<UserData>> = flow<ResultData<UserData>> {
        emit(userApi.getUserDetail().func(gson))
    }.flowOn(Dispatchers.IO)

    override fun updateUser(updateUserDto: UpdateUserDto): Flow<ResultData<MessageData>> =
        flow {
            emit(userApi.updateUser(updateUserDto).func(gson))
        }.flowOn(Dispatchers.IO)

    override fun getAllLastTransfers(): Flow<ResultData<List<LastTransferData>>> =
        flow {
            emit(userApi.getAllLastTransfers().func(gson))
        }.catch { error -> emit(ResultData.Error(error))}.flowOn(Dispatchers.IO)
}