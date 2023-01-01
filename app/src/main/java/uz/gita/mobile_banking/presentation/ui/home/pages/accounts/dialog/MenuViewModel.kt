package uz.gita.mobile_banking.presentation.ui.home.pages.accounts.dialog

import kotlinx.coroutines.flow.SharedFlow
import uz.gita.mobile_banking.presentation.ui.BaseViewModel
import uz.gita.mobile_banking.utils.MoreMenu

// Created by Jamshid Isoqov on 1/1/2023
interface MenuViewModel : BaseViewModel {

    val openQrScannerFlow: SharedFlow<Unit>

    fun navigateTo(moreMenu: MoreMenu)


}