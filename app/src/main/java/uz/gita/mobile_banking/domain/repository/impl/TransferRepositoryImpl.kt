package uz.gita.mobile_banking.domain.repository.impl

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.mobile_banking.data.remote.api.TransferApi
import uz.gita.mobile_banking.data.remote.request.transfer.*
import uz.gita.mobile_banking.data.remote.response.auth.TokenData
import uz.gita.mobile_banking.data.remote.response.transfer.PanData
import uz.gita.mobile_banking.data.remote.response.transfer.TransferFreeData
import uz.gita.mobile_banking.data.remote.response.transfer.TransferHistoryData
import uz.gita.mobile_banking.data.remote.response.user.MessageData
import uz.gita.mobile_banking.domain.repository.TransferRepository
import uz.gita.mobile_banking.utils.ResultData
import uz.gita.mobile_banking.utils.func
import javax.inject.Inject

class TransferRepositoryImpl @Inject constructor(
    private val api: TransferApi,
    private val gson: Gson
) : TransferRepository {
    override fun addPan(panDto: PanDto): Flow<ResultData<PanData>> = flow {
        emit(api.addPan(panDto).func(gson))
    }.flowOn(Dispatchers.IO)

    override fun getCardOwnerByPan(panDto: PanDto): Flow<ResultData<PanData>> = flow<ResultData<PanData>> {
        emit(api.getCardOwnerByPan(panDto).func(gson))
    }.flowOn(Dispatchers.IO)

    override fun addFreeTransfer(transferFreeDto: TransferFreeDto): Flow<ResultData<TransferFreeData>> =
        flow {
            emit(api.addFreeTransfer(transferFreeDto).func(gson))
        }.flowOn(Dispatchers.IO)

    override fun addTransfer(transferDto: TransferDto): Flow<ResultData<TokenData>> = flow {
        emit(api.addTransfer(transferDto).func(gson))
    }.flowOn(Dispatchers.IO)

    override fun transferVerify(transferVerifyDto: TransferVerifyDto): Flow<ResultData<MessageData>> =
        flow {
            emit(api.transferVerify(transferVerifyDto).func(gson))
        }.flowOn(Dispatchers.IO)

    override fun getTransfers(size: Int, currentPage: Int): Flow<ResultData<TransferHistoryData>> =
        flow {
            emit(api.getTransfers(size, currentPage).func(gson))
        }.flowOn(Dispatchers.IO)

    override fun resendTransfer(tokenDto: TokenDto): Flow<ResultData<TokenData>> = flow {
        emit(api.resendTransfer(tokenDto).func(gson))
    }.flowOn(Dispatchers.IO)
}