package uz.gita.mobile_banking.domain.usecase.impl

import uz.gita.mobile_banking.data.local.prefs.MySharedPrefs
import uz.gita.mobile_banking.domain.usecase.UserUseCase
import javax.inject.Inject

class UserUseCaseImpl @Inject constructor(
    private val mySharedPrefs: MySharedPrefs
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
}