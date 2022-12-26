package uz.gita.mobile_banking.domain.usecase

// Created by Jamshid Isoqov on 12/26/2022
interface StartScreenUseCase {
    suspend fun startScreen(): StartScreen
}

enum class StartScreen {
    PIN, LOGIN
}