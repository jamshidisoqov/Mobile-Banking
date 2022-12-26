package uz.gita.mobile_banking.presentation.ui.login

import uz.gita.mobile_banking.presentation.ui.BaseViewModel

// Created by Jamshid Isoqov on 12/25/2022
interface LoginViewModel : BaseViewModel {
    fun login(phone: String, password: String)
}