package uz.gita.mobile_banking.directions.impl

import uz.gita.mobile_banking.data.remote.response.card.CardData
import uz.gita.mobile_banking.directions.TransferScreenDirection
import uz.gita.mobile_banking.navigation.Navigator
import uz.gita.mobile_banking.presentation.ui.transfer.TransferScreenDirections
import javax.inject.Inject

class TransferScreenDirectionImp @Inject constructor(
    private val navigator: Navigator
) : TransferScreenDirection {
    override suspend fun navigateToVerify(
        cardData: CardData,
        receiverName: String,
        receiverPan: String,
        amount: Int,
        token: String
    ) {

        navigator.navigateTo(
            TransferScreenDirections.actionTransferScreenToTransferVerifyScreen(
                cardData = cardData,
                recipientName = receiverName,
                recipientPan = receiverPan,
                amount = amount,
                token = token
            )
        )

    }
}