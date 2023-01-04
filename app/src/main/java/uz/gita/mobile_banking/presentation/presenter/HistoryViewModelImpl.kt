package uz.gita.mobile_banking.presentation.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.mobile_banking.data.remote.response.transfer.TransferData
import uz.gita.mobile_banking.domain.usecase.TransferUseCase
import uz.gita.mobile_banking.presentation.ui.history.HistoryViewModel
import uz.gita.mobile_banking.utils.hasConnection
import javax.inject.Inject

@HiltViewModel
class HistoryViewModelImpl @Inject constructor(
    private val useCase: TransferUseCase
) : HistoryViewModel, ViewModel() {

    override val loadingSharedFlow = MutableSharedFlow<Boolean>()

    override val messageSharedFlow = MutableSharedFlow<String>()

    override val errorSharedFlow = MutableSharedFlow<String>()

    override val transferHistoryFlow = MutableSharedFlow<PagingData<TransferData>>()

    override fun getTransfers() {
        viewModelScope.launch(Dispatchers.IO) {
            if (hasConnection()) {
                useCase.getTransfers(0, 0).collectLatest {
                    transferHistoryFlow.emit(it)
                }
            } else {
                messageSharedFlow.emit("No internet connection")
            }
        }
    }


}