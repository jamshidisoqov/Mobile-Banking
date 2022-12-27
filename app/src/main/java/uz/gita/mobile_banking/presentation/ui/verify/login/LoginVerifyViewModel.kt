package uz.gita.mobile_banking.presentation.ui.verify.login

import kotlinx.coroutines.flow.StateFlow
import uz.gita.mobile_banking.presentation.ui.BaseViewModel

// Created by Jamshid Isoqov on 12/26/2022
interface LoginVerifyViewModel : BaseViewModel {

    val startTimer:StateFlow<Boolean>

    fun navigateToPin()

    fun verifyCode(code:String)

    fun resendCode()

}