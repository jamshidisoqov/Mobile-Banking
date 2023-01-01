package uz.gita.mobile_banking.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.mobile_banking.data.remote.response.exchange.ExchangeData
import uz.gita.mobile_banking.utils.ResultData

// Created by Jamshid Isoqov on 1/1/2023
interface GetAllExchanges {
    fun invoke():Flow<ResultData<List<ExchangeData>>>
}