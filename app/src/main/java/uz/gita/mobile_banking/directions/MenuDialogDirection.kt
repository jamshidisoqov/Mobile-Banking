package uz.gita.mobile_banking.directions

// Created by Jamshid Isoqov on 1/1/2023
interface MenuDialogDirection {

    suspend fun navigateToExchange()

    suspend fun navigateToHistory()

    suspend fun navigateToCards()

    suspend fun navigateToSavedPayments()

}