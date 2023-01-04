package uz.gita.mobile_banking.directions.impl

import uz.gita.mobile_banking.directions.MenuDialogDirection
import uz.gita.mobile_banking.navigation.Navigator
import uz.gita.mobile_banking.presentation.ui.home.HomeScreenDirections
import javax.inject.Inject

class MenuDialogDirectionImpl @Inject constructor(
    private val navigator: Navigator
): MenuDialogDirection {
    override suspend fun navigateToExchange() {
        navigator.navigateTo(HomeScreenDirections.actionHomeScreenToExchangeScreen())
    }

    override suspend fun navigateToHistory() {
       navigator.navigateTo(HomeScreenDirections.actionHomeScreenToHistoryScreen())
    }

    override suspend fun navigateToCards() {
        // TODO() navigate to
    }

    override suspend fun navigateToSavedPayments() {
        // TODO() navigate to
    }
}