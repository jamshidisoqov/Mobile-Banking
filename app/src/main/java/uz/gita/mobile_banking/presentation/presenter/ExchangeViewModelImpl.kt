package uz.gita.mobile_banking.presentation.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.mobile_banking.data.remote.response.exchange.ExchangeData
import uz.gita.mobile_banking.domain.usecase.GetAllExchanges
import uz.gita.mobile_banking.presentation.ui.exchange.ExchangeViewModel
import uz.gita.mobile_banking.utils.getMessage
import uz.gita.mobile_banking.utils.hasConnection
import javax.inject.Inject

@HiltViewModel
class ExchangeViewModelImpl @Inject constructor(
    private val useCase: GetAllExchanges
) : ExchangeViewModel, ViewModel() {

    override val loadingSharedFlow = MutableSharedFlow<Boolean>()

    override val messageSharedFlow = MutableSharedFlow<String>()

    override val errorSharedFlow = MutableSharedFlow<String>()

    override val allExchanges = MutableStateFlow<List<ExchangeData>>(emptyList())

    override fun getAllExchanges() {
        viewModelScope.launch {
            if (hasConnection()) {
                useCase.invoke().collectLatest { result ->
                    result.onSuccess {
                        allExchanges.emit(it)
                    }.onMessage {
                        messageSharedFlow.emit(it)
                    }.onError {
                        errorSharedFlow.emit(it.getMessage())
                    }
                }
            } else {
                messageSharedFlow.emit("No internet connection")
            }
        }
    }
}