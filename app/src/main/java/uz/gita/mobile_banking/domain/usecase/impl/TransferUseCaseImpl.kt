package uz.gita.mobile_banking.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.mobile_banking.data.remote.request.transfer.*
import uz.gita.mobile_banking.data.remote.response.auth.TokenData
import uz.gita.mobile_banking.data.remote.response.transfer.PanData
import uz.gita.mobile_banking.data.remote.response.transfer.TransferFreeData
import uz.gita.mobile_banking.data.remote.response.transfer.TransferHistoryData
import uz.gita.mobile_banking.data.remote.response.user.MessageData
import uz.gita.mobile_banking.domain.repository.TransferRepository
import uz.gita.mobile_banking.domain.source.PagingSourceData
import uz.gita.mobile_banking.domain.usecase.TransferUseCase
import uz.gita.mobile_banking.utils.ResultData
import javax.inject.Inject

class TransferUseCaseImpl @Inject constructor(
    private val repository: TransferRepository,
    private val pagingSourceData: PagingSourceData
) : TransferUseCase {
    override fun addPan(panDto: PanDto): Flow<ResultData<PanData>> = repository.addPan(panDto)

    override fun getCardOwnerByPan(panDto: PanDto): Flow<ResultData<PanData>> =
        repository.getCardOwnerByPan(panDto)

    override fun addFreeTransfer(transferFreeDto: TransferFreeDto): Flow<ResultData<TransferFreeData>> =
        repository.addFreeTransfer(transferFreeDto)

    override fun addTransfer(transferDto: TransferDto): Flow<ResultData<TokenData>> =
        repository.addTransfer(transferDto)

    override fun transferVerify(transferVerifyDto: TransferVerifyDto): Flow<ResultData<MessageData>> =
        repository.transferVerify(transferVerifyDto)

    override fun getTransfers(size: Int, currentPage: Int): Flow<ResultData<TransferHistoryData>> = flow {

    }
    override fun resendTransfer(tokenDto: TokenDto): Flow<ResultData<TokenData>> =
        repository.resendTransfer(tokenDto)
}