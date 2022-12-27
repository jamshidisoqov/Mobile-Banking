package uz.gita.mobile_banking.directions.impl

import uz.gita.mobile_banking.directions.PinScreenDirection
import uz.gita.mobile_banking.navigation.Navigator
import uz.gita.mobile_banking.presentation.ui.pin.PinScreenDirections
import javax.inject.Inject

class PinScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : PinScreenDirection {
    override suspend fun navigateToHome() {
        navigator.navigateTo(PinScreenDirections.actionPinScreenToHomeScreen())
    }
}