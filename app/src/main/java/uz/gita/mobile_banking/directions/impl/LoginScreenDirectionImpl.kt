package uz.gita.mobile_banking.directions.impl

import uz.gita.mobile_banking.directions.LoginScreenDirection
import uz.gita.mobile_banking.navigation.Navigator
import uz.gita.mobile_banking.presentation.ui.login.LoginScreenDirections
import javax.inject.Inject

class LoginScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : LoginScreenDirection {
    override suspend fun navigateToRegister() {
        navigator.navigateTo(LoginScreenDirections.actionLoginScreenToRegisterScreen())
    }

    override suspend fun navigateToVerify() {
        navigator.navigateTo(LoginScreenDirections.actionLoginScreenToLoginVerifyScreen())
    }
}