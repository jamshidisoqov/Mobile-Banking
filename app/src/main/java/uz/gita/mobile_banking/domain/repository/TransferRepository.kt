package uz.gita.mobile_banking.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.mobile_banking.data.remote.request.transfer.*
import uz.gita.mobile_banking.data.remote.response.auth.TokenData
import uz.gita.mobile_banking.data.remote.response.transfer.PanData
import uz.gita.mobile_banking.data.remote.response.transfer.TransferFreeData
import uz.gita.mobile_banking.data.remote.response.transfer.TransferHistoryData
import uz.gita.mobile_banking.data.remote.response.user.MessageData
import uz.gita.mobile_banking.utils.ResultData

// Created by Jamshid Isoqov on 12/25/2022
interface TransferRepository {

    fun addPan(panDto: PanDto): Flow<ResultData<PanData>>

    fun getCardOwnerByPan(panDto: PanDto): Flow<ResultData<PanData>>

    fun addFreeTransfer(transferFreeDto: TransferFreeDto): Flow<ResultData<TransferFreeData>>

    fun addTransfer(transferDto: TransferDto): Flow<ResultData<TokenData>>

    fun transferVerify(transferVerifyDto: TransferVerifyDto): Flow<ResultData<MessageData>>

    fun getTransfers(
        size: Int,
        currentPage: Int
    ): Flow<ResultData<TransferHistoryData>>

    fun resendTransfer(tokenDto: TokenDto): Flow<ResultData<TokenData>>


}