package uz.gita.mobile_banking.directions.impl

import uz.gita.mobile_banking.directions.RegisterVerifyScreenDirection
import uz.gita.mobile_banking.navigation.Navigator
import uz.gita.mobile_banking.presentation.ui.verify.register.RegisterVerifyScreenDirections
import javax.inject.Inject

class RegisterVerifyScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : RegisterVerifyScreenDirection {
    override suspend fun navigateToPin() {
        navigator.navigateTo(RegisterVerifyScreenDirections.actionRegisterVerifyScreenToPinScreen())
    }
}