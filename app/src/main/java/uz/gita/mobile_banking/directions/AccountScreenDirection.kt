package uz.gita.mobile_banking.directions

import uz.gita.mobile_banking.data.remote.response.card.CardData
import uz.gita.mobile_banking.data.remote.response.user.LastTransferData

// Created by Jamshid Isoqov on 12/28/2022
interface AccountScreenDirection {

    suspend fun navigateToAddCard()

    suspend fun navigateToCardDetail(cardData: CardData)

    suspend fun navigateToNotification()

    suspend fun navigateToPayment()

    suspend fun navigateToSend()

    suspend fun navigateToTransferDetail(transferData: LastTransferData)

    suspend fun navigateToSearch()

}