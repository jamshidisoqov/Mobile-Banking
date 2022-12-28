package uz.gita.mobile_banking.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.mobile_banking.data.local.prefs.MySharedPrefs
import uz.gita.mobile_banking.data.remote.request.user.UpdateUserDto
import uz.gita.mobile_banking.data.remote.response.user.*
import uz.gita.mobile_banking.domain.repository.UserRepository
import uz.gita.mobile_banking.domain.usecase.UserUseCase
import uz.gita.mobile_banking.utils.ResultData
import javax.inject.Inject

class UserUseCaseImpl @Inject constructor(
    private val mySharedPrefs: MySharedPrefs,
    private val repository: UserRepository
) : UserUseCase {
    override suspend fun setFirstName(firstName: String) {
        mySharedPrefs.firstName = firstName
    }

    override suspend fun setLastName(lastName: String) {
        mySharedPrefs.lastName = lastName
    }

    override suspend fun setPhoneNumber(phoneNumber: String) {
        mySharedPrefs.phoneNumber = phoneNumber
    }

    override suspend fun setPassword(password: String) {
        mySharedPrefs.password = password
    }

    override suspend fun setBornDate(bornDate: Long) {
        mySharedPrefs.bornDate = bornDate
    }

    override suspend fun setGender(gender: Int) {
        mySharedPrefs.gender = gender
    }

    override suspend fun getFirstName(): String =
        mySharedPrefs.firstName

    override suspend fun getLastName(): String =
        mySharedPrefs.lastName

    override suspend fun getPhoneNumber(): String =
        mySharedPrefs.phoneNumber

    override suspend fun getPassword(): String =
        mySharedPrefs.password

    override suspend fun getBornDate(): Long =
        mySharedPrefs.bornDate

    override suspend fun getGender(): Int =
        mySharedPrefs.gender

    override fun getCurrentBalance(): Flow<ResultData<BalanceData>> = repository.getCurrentBalance()

    override fun getUserInfo(): Flow<ResultData<ProfileData>> = repository.getUserInfo()

    override fun getUserDetail(): Flow<ResultData<UserData>> = repository.getUserDetail()

    override fun updateUser(updateUserDto: UpdateUserDto): Flow<ResultData<MessageData>> =
        repository.updateUser(updateUserDto)

    override fun getAllLastTransfers(): Flow<ResultData<List<LastTransferData>>> =
        repository.getAllLastTransfers()
}