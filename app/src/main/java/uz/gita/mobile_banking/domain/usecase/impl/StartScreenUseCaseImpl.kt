package uz.gita.mobile_banking.domain.usecase.impl

import uz.gita.mobile_banking.data.local.prefs.MySharedPrefs
import uz.gita.mobile_banking.domain.usecase.StartScreen
import uz.gita.mobile_banking.domain.usecase.StartScreenUseCase
import javax.inject.Inject

class StartScreenUseCaseImpl @Inject constructor(
    private val mySharedPrefs: MySharedPrefs
) : StartScreenUseCase {
    override suspend fun startScreen(): StartScreen =
        if (mySharedPrefs.refreshToken.isNotEmpty()) {
            StartScreen.PIN
        } else {
            StartScreen.LOGIN
        }
}