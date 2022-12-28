package uz.gita.mobile_banking.presentation.ui.card.add

import kotlinx.coroutines.flow.SharedFlow
import uz.gita.mobile_banking.presentation.ui.BaseViewModel

// Created by Jamshid Isoqov on 12/28/2022
interface AddCardViewModel : BaseViewModel {

    val backSharedFlow:SharedFlow<String>

    fun addCard(cardName: String, cardNumber: String, expiredMonth: String, expiredYear: String)

}