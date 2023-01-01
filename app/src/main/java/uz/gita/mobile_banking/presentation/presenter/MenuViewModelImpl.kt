package uz.gita.mobile_banking.presentation.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import uz.gita.mobile_banking.directions.MenuDialogDirection
import uz.gita.mobile_banking.presentation.ui.home.pages.accounts.dialog.MenuViewModel
import uz.gita.mobile_banking.utils.MoreMenu
import javax.inject.Inject

@HiltViewModel
class MenuViewModelImpl @Inject constructor(
    private val direction: MenuDialogDirection
) : MenuViewModel, ViewModel() {

    override val loadingSharedFlow = MutableSharedFlow<Boolean>()

    override val messageSharedFlow = MutableSharedFlow<String>()

    override val errorSharedFlow = MutableSharedFlow<String>()

    override val openQrScannerFlow = MutableSharedFlow<Unit>()


    override fun navigateTo(moreMenu: MoreMenu) {
        viewModelScope.launch {
            when (moreMenu) {
                MoreMenu.EXCHANGE -> {
                    direction.navigateToExchange()
                }
                MoreMenu.CARDS -> {
                    direction.navigateToCards()
                }
                MoreMenu.QR_PAYMENT -> {
                    openQrScannerFlow.emit(Unit)
                }
                MoreMenu.HISTORY -> {
                    direction.navigateToHistory()
                }
                MoreMenu.SAVED_PAYMENT -> {
                    direction.navigateToSavedPayments()
                }
            }
        }
    }
}