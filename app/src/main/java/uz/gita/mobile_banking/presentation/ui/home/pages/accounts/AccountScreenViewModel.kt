package uz.gita.mobile_banking.presentation.ui.home.pages.accounts

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.mobile_banking.data.remote.response.card.CardData
import uz.gita.mobile_banking.data.remote.response.user.LastTransferData
import uz.gita.mobile_banking.presentation.ui.BaseViewModel

// Created by Jamshid Isoqov on 12/27/2022

interface AccountScreenViewModel : BaseViewModel {

    val cardsList: StateFlow<List<CardData>>

    val lastTransactions:StateFlow<List<LastTransferData>>

    val openMoreDialog:SharedFlow<Unit>

    fun getCards()

    fun getLastTransfers()

    fun navigateToAddCard()

    fun navigateToPayment()

    fun navigateToSend()

    fun clickMore()

    fun navigateToNotification()

    fun searchLastTransfers()

    fun navigateToCardDetails(cardData: CardData)

    fun navigateToLastTransfersDetail(lastTransferData: LastTransferData)

}
