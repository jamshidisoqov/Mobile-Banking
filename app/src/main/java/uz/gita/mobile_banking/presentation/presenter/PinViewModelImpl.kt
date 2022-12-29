package uz.gita.mobile_banking.presentation.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import uz.gita.mobile_banking.directions.PinScreenDirection
import uz.gita.mobile_banking.domain.usecase.AuthUseCase
import uz.gita.mobile_banking.domain.usecase.UserUseCase
import uz.gita.mobile_banking.presentation.ui.pin.PinViewModel
import javax.inject.Inject

@HiltViewModel
class PinViewModelImpl @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val direction: PinScreenDirection,
    private val useCase: UserUseCase
) : PinViewModel, ViewModel() {

    override val isFirstSharedFlow = MutableSharedFlow<Boolean>()

    override val errorSharedFlow  = MutableSharedFlow<String>()


    override fun navigateToMain(password: String) {
        viewModelScope.launch {
            useCase.setPassword(password)
            direction.navigateToHome()
        }
    }

    override fun savePassword(password: String) {
        viewModelScope.launch {
            useCase.setPassword(password)
            direction.navigateToHome()
        }
    }

    override fun getIsFirst() {
        viewModelScope.launch {
            isFirstSharedFlow.emit(authUseCase.getAccessToken().isEmpty())
        }
    }

    override fun check(password: String) {
        viewModelScope.launch {
            if (useCase.getPassword()==password){
                direction.navigateToHome()
            }else{
                errorSharedFlow.emit("Incorrect password")
            }
        }
    }
}