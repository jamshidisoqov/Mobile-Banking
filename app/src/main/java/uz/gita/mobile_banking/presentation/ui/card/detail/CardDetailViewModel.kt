package uz.gita.mobile_banking.presentation.ui.card.detail

import androidx.paging.PagingData
import kotlinx.coroutines.flow.StateFlow
import uz.gita.mobile_banking.data.remote.response.card.CardData
import uz.gita.mobile_banking.data.remote.response.transfer.TransferData
import uz.gita.mobile_banking.presentation.ui.BaseViewModel

// Created by Jamshid Isoqov on 1/4/2023
interface CardDetailViewModel : BaseViewModel {

    val transfersByCard: StateFlow<PagingData<TransferData>>

    fun getTransfersByCard(cardData: CardData)


}