package uz.gita.mobile_banking.presentation.ui.history

import androidx.paging.PagingData
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.mobile_banking.data.remote.response.transfer.TransferData
import uz.gita.mobile_banking.presentation.ui.BaseViewModel

// Created by Jamshid Isoqov on 1/3/2023
interface HistoryViewModel : BaseViewModel {

    val transferHistoryFlow: SharedFlow<PagingData<TransferData>>

    fun getTransfers()

}