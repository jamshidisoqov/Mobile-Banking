package uz.gita.mobile_banking.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.mobile_banking.data.remote.response.exchange.ExchangeData
import uz.gita.mobile_banking.domain.repository.ExchangeRepository
import uz.gita.mobile_banking.domain.usecase.GetAllExchanges
import uz.gita.mobile_banking.utils.ResultData
import javax.inject.Inject

class GetAllExchangesImpl @Inject constructor(
    private val repository: ExchangeRepository
) : GetAllExchanges {
    override fun invoke(): Flow<ResultData<List<ExchangeData>>> =
        repository.getAllExchanges()
}