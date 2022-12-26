package uz.gita.mobile_banking.presentation.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.mobile_banking.directions.RegisterScreenDirection
import uz.gita.mobile_banking.domain.usecase.AuthUseCase
import uz.gita.mobile_banking.domain.usecase.UserUseCase
import uz.gita.mobile_banking.presentation.ui.register.RegisterViewModel
import uz.gita.mobile_banking.utils.getMessage
import uz.gita.mobile_banking.utils.hasConnection
import javax.inject.Inject

@HiltViewModel
class RegisterViewModelImpl @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val userUseCase: UserUseCase,
    private val direction: RegisterScreenDirection
) : RegisterViewModel, ViewModel() {

    override val loadingSharedFlow = MutableSharedFlow<Boolean>()

    override val messageSharedFlow = MutableSharedFlow<String>()

    override val errorSharedFlow = MutableSharedFlow<String>()

    override fun register(
        phone: String,
        password: String,
        firstName: String,
        lastName: String,
        bornDate: Long,
        gender: Int
    ) {
        viewModelScope.launch {
            if (hasConnection()) {
                authUseCase.register(
                    phone = phone,
                    password = password,
                    firstName = firstName,
                    lastName = lastName,
                    bornDate = bornDate,
                    gender = gender
                ).collectLatest { result ->
                    result.onSuccess {
                        userUseCase.setFirstName(firstName)
                        userUseCase.setLastName(lastName)
                        userUseCase.setPassword(password)
                        userUseCase.setGender(gender)
                        userUseCase.setBornDate(bornDate)
                        userUseCase.setPhoneNumber(phone)
                        authUseCase.setToken(it.token)
                        direction.navigateToVerify()
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

    override fun navigateToSignIn() {
        viewModelScope.launch {
            direction.navigateToLogin()
        }
    }
}