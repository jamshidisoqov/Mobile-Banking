package uz.gita.mobile_banking.presentation.ui.pin

import kotlinx.coroutines.flow.SharedFlow

// Created by Jamshid Isoqov on 12/27/2022
interface PinViewModel {

    val isFirstSharedFlow: SharedFlow<Boolean>

    val errorSharedFlow:SharedFlow<String>

    val backSharedFlow:SharedFlow<Boolean>

    fun navigateToMain(password: String)

    fun savePassword(password: String)

    fun getIsFirst()

    fun check(password: String)

}