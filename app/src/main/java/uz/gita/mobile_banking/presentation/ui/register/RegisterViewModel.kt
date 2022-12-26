package uz.gita.mobile_banking.presentation.ui.register

import uz.gita.mobile_banking.presentation.ui.BaseViewModel

// Created by Jamshid Isoqov on 12/26/2022
interface RegisterViewModel : BaseViewModel {

    fun register(
        phone: String,
        password: String,
        firstName: String,
        lastName: String,
        bornDate: Long,
        gender: Int
    )

    fun navigateToSignIn()

}