package uz.gita.mobile_banking.presentation.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.mobile_banking.data.remote.response.card.CardData
import uz.gita.mobile_banking.data.remote.response.user.LastTransferData
import uz.gita.mobile_banking.directions.AccountScreenDirection
import uz.gita.mobile_banking.domain.usecase.CardUseCase
import uz.gita.mobile_banking.domain.usecase.UserUseCase
import uz.gita.mobile_banking.presentation.ui.home.pages.accounts.AccountScreenViewModel
import uz.gita.mobile_banking.utils.getMessage
import uz.gita.mobile_banking.utils.hasConnection
import javax.inject.Inject

@HiltViewModel
class AccountScreenViewModelImpl @Inject constructor(
    private val cardUseCase: CardUseCase,
    private val userUseCase: UserUseCase,
    private val direction: AccountScreenDirection
) : AccountScreenViewModel, ViewModel() {

    override val loadingSharedFlow = MutableSharedFlow<Boolean>()

    override val messageSharedFlow = MutableSharedFlow<String>()

    override val errorSharedFlow = MutableSharedFlow<String>()

    override val cardsList = MutableStateFlow<List<CardData>>(emptyList())

    override val lastTransactions = MutableStateFlow<List<LastTransferData>>(emptyList())

    override val errorCardsMessage = MutableSharedFlow<String>()

    override val openMoreDialog = MutableSharedFlow<Unit>()


    override fun getCards() {
        viewModelScope.launch {
            if (hasConnection()) {
                loadingSharedFlow.emit(true)
                cardUseCase.getAllCards().collectLatest { result ->
                    loadingSharedFlow.emit(false)
                    result.onSuccess {
                        cardsList.emit(it)
                    }.onMessage {
                        errorCardsMessage.emit(it)
                    }.onError {
                        errorCardsMessage.emit(it.getMessage())
                    }
                }
            } else {
                messageSharedFlow.emit("No internet connection")
            }
        }
    }

    override fun getLastTransfers() {
        viewModelScope.launch {
            if (hasConnection()) {
                userUseCase.getAllLastTransfers().collectLatest { result ->
                    result.onSuccess {
                        lastTransactions.emit(it)
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

    override fun navigateToAddCard() {
        viewModelScope.launch {
            direction.navigateToAddCard()
        }
    }

    override fun navigateToPayment() {
        viewModelScope.launch {
            direction.navigateToPayment()
        }
    }

    override fun navigateToSend() {
        viewModelScope.launch {
            direction.navigateToSend()
        }
    }

    override fun clickMore() {
        viewModelScope.launch {
           openMoreDialog.emit(Unit)
        }
    }

    override fun navigateToNotification() {
        viewModelScope.launch {
            direction.navigateToNotification()
        }
    }

    override fun searchLastTransfers() {
        viewModelScope.launch {
            direction.navigateToSearch()
        }
    }

    override fun navigateToCardDetails(cardData: CardData) {
        viewModelScope.launch {
            direction.navigateToCardDetail(cardData)
        }
    }

    override fun navigateToLastTransfersDetail(lastTransferData: LastTransferData) {
        viewModelScope.launch {
            direction.navigateToTransferDetail(lastTransferData)
        }
    }
}