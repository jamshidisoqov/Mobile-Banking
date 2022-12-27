package uz.gita.mobile_banking.presentation.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.mobile_banking.directions.LoginVerifyScreenDirection
import uz.gita.mobile_banking.domain.usecase.AuthUseCase
import uz.gita.mobile_banking.presentation.ui.verify.login.LoginVerifyViewModel
import uz.gita.mobile_banking.utils.getMessage
import uz.gita.mobile_banking.utils.hasConnection
import javax.inject.Inject

@HiltViewModel
class LoginVerifyViewModelImpl @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val direction: LoginVerifyScreenDirection
) : LoginVerifyViewModel, ViewModel() {

    override val loadingSharedFlow = MutableSharedFlow<Boolean>()

    override val messageSharedFlow = MutableSharedFlow<String>()

    override val errorSharedFlow = MutableSharedFlow<String>()

    override val startTimer = MutableStateFlow(true)

    override fun navigateToPin() {
        viewModelScope.launch {
            direction.navigateToPin()
        }
    }

    override fun verifyCode(code: String) {
        viewModelScope.launch {
            if (hasConnection()) {
                loadingSharedFlow.emit(true)
                authUseCase.loginVerify(code).collectLatest { result ->
                    loadingSharedFlow.emit(false)
                    result.onSuccess {
                        authUseCase.setAccessToken(it.accessToken)
                        authUseCase.setRefreshToken(it.refreshToken)
                        navigateToPin()
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
            authUseCase.loginResendCode()
            startTimer.emit(true)
        }
    }
}