package uz.gita.mobile_banking.directions.impl

import uz.gita.mobile_banking.directions.SplashScreenDirection
import uz.gita.mobile_banking.navigation.Navigator
import uz.gita.mobile_banking.presentation.ui.splash.SplashScreenDirections
import javax.inject.Inject

class SplashScreenDirectionsImpl @Inject constructor(
    private val navigator: Navigator
) : SplashScreenDirection {
    override suspend fun navigateToPassword() {
        navigator.navigateTo(SplashScreenDirections.actionSplashScreenToPinScreen())
    }

    override suspend fun navigateToLogin() {
        navigator.navigateTo(SplashScreenDirections.actionSplashScreenToLoginScreen())
    }
}