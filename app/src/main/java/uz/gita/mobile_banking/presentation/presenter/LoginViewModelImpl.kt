package uz.gita.mobile_banking.presentation.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.mobile_banking.domain.usecase.AuthUseCase
import uz.gita.mobile_banking.presentation.ui.login.LoginViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModelImpl @Inject constructor(
    private val useCase: AuthUseCase
) : LoginViewModel,ViewModel() {
    override fun login(phone: String, password: String) {
        viewModelScope.launch {
            useCase.login(phone, password).collectLatest {result->
                result.onSuccess {
                    //TODO work
                }.onMessage {
                    //TODO work
                }.onError {
                    //TODO work
                }
            }
        }
    }
}