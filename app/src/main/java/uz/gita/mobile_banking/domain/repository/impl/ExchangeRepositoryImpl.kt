package uz.gita.mobile_banking.domain.repository.impl

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.mobile_banking.data.remote.api.ExchangeApi
import uz.gita.mobile_banking.data.remote.response.exchange.ExchangeData
import uz.gita.mobile_banking.domain.repository.ExchangeRepository
import uz.gita.mobile_banking.utils.ResultData
import uz.gita.mobile_banking.utils.func
import javax.inject.Inject

class ExchangeRepositoryImpl @Inject constructor(
    private val exchangeApi: ExchangeApi,
    private val gson: Gson
) : ExchangeRepository {
    override fun getAllExchanges(): Flow<ResultData<List<ExchangeData>>>  = flow {
       val response = exchangeApi.getAllCurrency()
        if (response.isSuccessful){
            if (response.body()!=null){
                emit(ResultData.Success(response.body()!!))
            }else{
                emit(ResultData.Error(Throwable("Body null")))
            }
        }else{
            emit(ResultData.Message("Unknown error"))
        }
    }.flowOn(Dispatchers.IO)
}