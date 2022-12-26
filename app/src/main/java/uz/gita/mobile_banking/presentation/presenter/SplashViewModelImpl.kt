package uz.gita.mobile_banking.presentation.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.gita.mobile_banking.directions.SplashScreenDirection
import uz.gita.mobile_banking.domain.usecase.StartScreen
import uz.gita.mobile_banking.domain.usecase.StartScreenUseCase
import uz.gita.mobile_banking.presentation.ui.splash.SplashViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val useCase: StartScreenUseCase,
    private val directions: SplashScreenDirection
) : SplashViewModel, ViewModel() {
    override fun navigate() {
        viewModelScope.launch {
            when (useCase.startScreen()) {
                StartScreen.PIN -> directions.navigateToPassword()
                StartScreen.LOGIN -> directions.navigateToLogin()
            }
        }
    }
}