package uz.gita.mobile_banking.presentation.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.mobile_banking.directions.LoginScreenDirection
import uz.gita.mobile_banking.domain.usecase.AuthUseCase
import uz.gita.mobile_banking.presentation.ui.login.LoginViewModel
import uz.gita.mobile_banking.utils.getMessage
import javax.inject.Inject

@HiltViewModel
class LoginViewModelImpl @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val direction: LoginScreenDirection
) : LoginViewModel, ViewModel() {

    override val loadingSharedFlow = MutableSharedFlow<Boolean>()

    override val messageSharedFlow = MutableSharedFlow<String>()

    override val errorSharedFlow = MutableSharedFlow<String>()


    override fun login(phone: String, password: String) {
        viewModelScope.launch {
            authUseCase.login(phone, password).collectLatest { result ->
                result.onSuccess {
                    authUseCase.setToken(it.token)
                    direction.navigateToVerify()
                }.onMessage {
                    messageSharedFlow.emit(it)
                }.onError {
                    errorSharedFlow.emit(it.getMessage())
                }
            }
        }
    }

    override fun navigateToRegister() {
        viewModelScope.launch {
            direction.navigateToRegister()
        }
    }

    override fun forgetPassword() {
        viewModelScope.launch {

        }
    }
}