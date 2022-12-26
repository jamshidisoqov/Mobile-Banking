package uz.gita.mobile_banking.directions.impl

import uz.gita.mobile_banking.directions.RegisterScreenDirection
import uz.gita.mobile_banking.navigation.Navigator
import uz.gita.mobile_banking.presentation.ui.register.RegisterScreenDirections
import javax.inject.Inject

class RegisterScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : RegisterScreenDirection {
    override suspend fun navigateToLogin() {
        navigator.navigateTo(RegisterScreenDirections.actionRegisterScreenToLoginScreen())
    }

    override suspend fun navigateToVerify() {
        navigator.navigateTo(RegisterScreenDirections.actionRegisterScreenToRegisterVerifyScreen())
    }
}