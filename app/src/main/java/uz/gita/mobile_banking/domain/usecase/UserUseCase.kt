package uz.gita.mobile_banking.domain.usecase

// Created by Jamshid Isoqov on 12/25/2022
interface UserUseCase {

    suspend fun setFirstName(firstName: String)

    suspend fun setLastName(lastName: String)

    suspend fun setPhoneNumber(phoneNumber: String)

    suspend fun setPassword(password: String)

    suspend fun setBornDate(bornDate: Long)

    suspend fun setGender(gender: Int)

    suspend fun getFirstName(): String

    suspend fun getLastName(): String

    suspend fun getPhoneNumber(): String

    suspend fun getPassword(): String

    suspend fun getBornDate(): Long

    suspend fun getGender(): Int

}