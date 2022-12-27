package uz.gita.mobile_banking.directions.impl

import uz.gita.mobile_banking.directions.LoginVerifyScreenDirection
import uz.gita.mobile_banking.navigation.Navigator
import uz.gita.mobile_banking.presentation.ui.verify.login.LoginVerifyScreenDirections
import javax.inject.Inject

class LoginVerifyScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : LoginVerifyScreenDirection {
    override suspend fun navigateToPin() {
        navigator.navigateTo(LoginVerifyScreenDirections.actionLoginVerifyScreenToPinScreen())
    }
}