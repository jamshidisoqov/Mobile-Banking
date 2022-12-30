package uz.gita.mobile_banking.directions

import uz.gita.mobile_banking.data.remote.response.card.CardData

// Created by Jamshid Isoqov on 12/30/2022
interface TransferScreenDirection {

    suspend fun navigateToVerify(
        cardData: CardData,
        receiverName: String,
        receiverPan: String,
        amount: Int,
        token: String
    )

}