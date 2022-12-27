package uz.gita.mobile_banking.presentation.ui.verify.register

import kotlinx.coroutines.flow.StateFlow
import uz.gita.mobile_banking.presentation.ui.BaseViewModel

// Created by Jamshid Isoqov on 12/27/2022
interface RegisterVerifyViewModel : BaseViewModel {

    val startTimer: StateFlow<Boolean>

    fun verifyCode(code:String)

    fun resendCode()

}