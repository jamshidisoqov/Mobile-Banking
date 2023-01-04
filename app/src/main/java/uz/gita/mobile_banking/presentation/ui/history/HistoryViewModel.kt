package uz.gita.mobile_banking.presentation.ui.history

import kotlinx.coroutines.flow.StateFlow
import uz.gita.mobile_banking.data.remote.response.transfer.TransferHistoryData
import uz.gita.mobile_banking.presentation.ui.BaseViewModel

// Created by Jamshid Isoqov on 1/3/2023
interface HistoryViewModel : BaseViewModel {

    val transferHistoryFlow:StateFlow<TransferHistoryData>


}