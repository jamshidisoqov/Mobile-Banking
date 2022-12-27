package uz.gita.mobile_banking.presentation.presenterl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.mobile_banking.directions.RegisterVerifyScreenDirection
import uz.gita.mobile_banking.domain.usecase.AuthUseCase
import uz.gita.mobile_banking.presentation.ui.verify.register.RegisterVerifyViewModel
import uz.gita.mobile_banking.utils.getMessage
import uz.gita.mobile_banking.utils.hasConnection
import javax.inject.Inject

@HiltViewModel
class RegisterVerifyViewModelImpl @Inject constructor(
    private val direction: RegisterVerifyScreenDirection,
    private val authUseCase: AuthUseCase
) : RegisterVerifyViewModel, ViewModel() {
    override val loadingSharedFlow = MutableSharedFlow<Boolean>()

    override val messageSharedFlow = MutableSharedFlow<String>()

    override val errorSharedFlow = MutableSharedFlow<String>()

    override val startTimer = MutableStateFlow(true)

    override fun verifyCode(code: String) {
        viewModelScope.launch {
            if (hasConnection()) {
                loadingSharedFlow.emit(true)
                authUseCase.loginVerify(code).collectLatest { result ->
                    loadingSharedFlow.emit(false)
                    result.onSuccess {
                        authUseCase.setAccessToken(it.accessToken)
                        authUseCase.setRefreshToken(it.refreshToken)
                        direction.navigateToPin()
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
            authUseCase.registerResendCode()
            startTimer.emit(true)
        }
    }
}