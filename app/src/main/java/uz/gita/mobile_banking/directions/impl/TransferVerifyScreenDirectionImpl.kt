package uz.gita.mobile_banking.directions.impl

import uz.gita.mobile_banking.directions.TransferVerifyScreenDirection
import uz.gita.mobile_banking.navigation.Navigator
import uz.gita.mobile_banking.presentation.ui.transfer.verify.TransferVerifyScreenDirections
import javax.inject.Inject

class TransferVerifyScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : TransferVerifyScreenDirection {
    override suspend fun navigateToMain() {
        navigator.navigateTo(TransferVerifyScreenDirections.actionTransferVerifyScreenToHomeScreen())
    }
}