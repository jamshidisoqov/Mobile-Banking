package uz.gita.mobile_banking.presentation.ui.exchange

import kotlinx.coroutines.flow.StateFlow
import uz.gita.mobile_banking.data.remote.response.exchange.ExchangeData
import uz.gita.mobile_banking.presentation.ui.BaseViewModel

// Created by Jamshid Isoqov on 1/1/2023
interface ExchangeViewModel : BaseViewModel {

    val allExchanges:StateFlow<List<ExchangeData>>

    fun getAllExchanges()
}