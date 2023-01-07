package uz.gita.mobile_banking.presentation.ui.home.pages.statistics

import kotlinx.coroutines.flow.SharedFlow
import uz.gita.mobile_banking.presentation.ui.BaseViewModel

// Created by Jamshid Isoqov on 1/6/2023
interface StatisticsViewModel : BaseViewModel {

    val currentMonthFlow: SharedFlow<String>

    val incomeTransfersFlow: SharedFlow<Int>

    val expansesTransfersFlow: SharedFlow<Int>

    val paymentsFlow: SharedFlow<Int>

    fun getStatisticsByRangeDate()

}