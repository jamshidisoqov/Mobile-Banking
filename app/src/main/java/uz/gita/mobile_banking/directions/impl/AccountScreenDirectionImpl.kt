package uz.gita.mobile_banking.directions.impl

import uz.gita.mobile_banking.data.remote.response.card.CardData
import uz.gita.mobile_banking.data.remote.response.user.LastTransferData
import uz.gita.mobile_banking.directions.AccountScreenDirection
import uz.gita.mobile_banking.navigation.Navigator
import uz.gita.mobile_banking.presentation.ui.home.HomeScreenDirections
import javax.inject.Inject

class AccountScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : AccountScreenDirection {

    override suspend fun navigateToAddCard() {
        navigator.navigateTo(HomeScreenDirections.actionHomeScreenToAddCardScreen())
    }

    override suspend fun navigateToCardDetail(cardData: CardData) {
        navigator.navigateTo(HomeScreenDirections.actionHomeScreenToCardDetailsScreen(cardData))
    }

    override suspend fun navigateToNotification() {

    }

    override suspend fun navigateToPayment() {

    }

    override suspend fun navigateToSend() {
        navigator.navigateTo(HomeScreenDirections.actionHomeScreenToTransferScreen())
    }

    override suspend fun navigateToTransferDetail(transferData: LastTransferData) {

    }

    override suspend fun navigateToSearch() {

    }
}