package uz.gita.mobile_banking.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.mobile_banking.data.remote.request.transfer.PanDto
import uz.gita.mobile_banking.data.remote.request.transfer.TransferDto
import uz.gita.mobile_banking.data.remote.request.transfer.TransferFreeDto
import uz.gita.mobile_banking.data.remote.request.transfer.TransferVerifyDto
import uz.gita.mobile_banking.data.remote.response.auth.TokenData
import uz.gita.mobile_banking.data.remote.response.transfer.PanData
import uz.gita.mobile_banking.data.remote.response.transfer.TransferFreeData
import uz.gita.mobile_banking.data.remote.response.transfer.TransferHistoryData
import uz.gita.mobile_banking.data.remote.response.user.MessageData
import uz.gita.mobile_banking.domain.repository.TransferRepository
import uz.gita.mobile_banking.domain.usecase.TransferUseCase
import uz.gita.mobile_banking.utils.ResultData
import javax.inject.Inject

class TransferUseCaseImpl @Inject constructor(
    private val repository: TransferRepository
) : TransferUseCase {
    override fun addPan(panDto: PanDto): Flow<ResultData<PanData>> = repository.addPan(panDto)

    override fun addFreeTransfer(transferFreeDto: TransferFreeDto): Flow<ResultData<TransferFreeData>> =
        repository.addFreeTransfer(transferFreeDto)

    override fun addTransfer(transferDto: TransferDto): Flow<ResultData<TokenData>> =
        repository.addTransfer(transferDto)

    override fun transferVerify(transferVerifyDto: TransferVerifyDto): Flow<ResultData<MessageData>> =
        repository.transferVerify(transferVerifyDto)

    override fun getTransfers(size: Int, currentPage: Int): Flow<ResultData<TransferHistoryData>> =
        repository.getTransfers(size, currentPage)

    override fun resendTransfer(transfer: TransferDto): Flow<ResultData<TokenData>> =
        repository.resendTransfer(transfer)
}