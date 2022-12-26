package uz.gita.mobile_banking.presentation.ui

import kotlinx.coroutines.flow.SharedFlow

// Created by Jamshid Isoqov on 12/23/2022
interface BaseViewModel {

    val loadingSharedFlow:SharedFlow<Boolean>

    val messageSharedFlow:SharedFlow<String>

    val errorSharedFlow:SharedFlow<String>

}