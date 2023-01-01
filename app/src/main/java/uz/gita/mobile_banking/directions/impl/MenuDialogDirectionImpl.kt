package uz.gita.mobile_banking.directions.impl

import uz.gita.mobile_banking.directions.MenuDialogDirection
import uz.gita.mobile_banking.navigation.Navigator
import javax.inject.Inject

class MenuDialogDirectionImpl @Inject constructor(
    private val navigator: Navigator
): MenuDialogDirection {
    override suspend fun navigateToExchange() {
        // TODO() navigate to
    }

    override suspend fun navigateToHistory() {
        // TODO() navigate to
    }

    override suspend fun navigateToCards() {
        // TODO() navigate to
    }

    override suspend fun navigateToSavedPayments() {
        // TODO() navigate to
    }
}