package uz.gita.mobile_banking.domain.usecase.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import uz.gita.mobile_banking.data.remote.request.transfer.*
import uz.gita.mobile_banking.data.remote.response.auth.TokenData
import uz.gita.mobile_banking.data.remote.response.card.CardData
import uz.gita.mobile_banking.data.remote.response.transfer.PanData
import uz.gita.mobile_banking.data.remote.response.transfer.TransferData
import uz.gita.mobile_banking.data.remote.response.transfer.TransferFreeData
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

    override fun getTransfers(size: Int, currentPage: Int): Flow<PagingData<TransferData>> =
        Pager(PagingConfig(pageSize = 20)) {
            pagingSourceData
        }.flow

    override fun resendTransfer(tokenDto: TokenDto): Flow<ResultData<TokenData>> =
        repository.resendTransfer(tokenDto)

    override fun getTransfersByCard(cardData: CardData): Flow<PagingData<TransferData>> =
        getTransfers(0, 0).onEach { page ->
            page.filter {
                it.from == cardData.pan
            }
        }.flowOn(Dispatchers.IO)
}