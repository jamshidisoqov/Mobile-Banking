package uz.gita.mobile_banking.presentation.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.mobile_banking.data.remote.request.transfer.TokenDto
import uz.gita.mobile_banking.data.remote.request.transfer.TransferVerifyDto
import uz.gita.mobile_banking.domain.usecase.TransferUseCase
import uz.gita.mobile_banking.presentation.ui.transfer.verify.TransferVerifyViewModel
import uz.gita.mobile_banking.utils.getMessage
import uz.gita.mobile_banking.utils.hasConnection
import javax.inject.Inject

@HiltViewModel
class TransferVerifyViewModelImpl @Inject constructor(
    private val transferUseCase: TransferUseCase
) : TransferVerifyViewModel, ViewModel() {

    override val loadingSharedFlow = MutableSharedFlow<Boolean>()

    override val messageSharedFlow = MutableSharedFlow<String>()

    override val errorSharedFlow = MutableSharedFlow<String>()

    override val backSharedFlow = MutableSharedFlow<Unit>()

    override val startTimer = MutableStateFlow(true)

    override val tokenFlow = MutableSharedFlow<String>()

    private var token: String = ""

    override fun verify(token: String, code: String) {
        this.token = token
        viewModelScope.launch {
            if (hasConnection()) {
                loadingSharedFlow.emit(true)
                transferUseCase.transferVerify(TransferVerifyDto(token, code))
                    .collectLatest { result ->
                        loadingSharedFlow.emit(false)
                        result.onSuccess {
                            backSharedFlow.emit(Unit)
                            messageSharedFlow.emit(it.message)
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

    override fun resendCode() {
        viewModelScope.launch {
            if (hasConnection()) {
                loadingSharedFlow.emit(true)
                transferUseCase.resendTransfer(TokenDto(token)).collectLatest { result ->
                    loadingSharedFlow.emit(false)
                    result.onSuccess {
                        startTimer.emit(true)
                        tokenFlow.emit(it.token)
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