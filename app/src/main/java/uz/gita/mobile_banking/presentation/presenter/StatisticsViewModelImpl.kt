package uz.gita.mobile_banking.presentation.presenter

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import uz.gita.mobile_banking.presentation.ui.home.pages.statistics.StatisticsViewModel
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModelImpl @Inject constructor(

) : StatisticsViewModel, ViewModel() {

    override val loadingSharedFlow = MutableSharedFlow<Boolean>()

    override val messageSharedFlow = MutableSharedFlow<String>()

    override val errorSharedFlow = MutableSharedFlow<String>()

    override val currentMonthFlow = MutableSharedFlow<String>()

    override val incomeTransfersFlow = MutableSharedFlow<Int>()

    override val expansesTransfersFlow = MutableSharedFlow<Int>()

    override val paymentsFlow = MutableSharedFlow<Int>()

    override fun getStatisticsByRangeDate() {

    }
}