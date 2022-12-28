package uz.gita.mobile_banking.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.mobile_banking.domain.usecase.AuthUseCase
import uz.gita.mobile_banking.domain.usecase.CardUseCase
import uz.gita.mobile_banking.domain.usecase.StartScreenUseCase
import uz.gita.mobile_banking.domain.usecase.UserUseCase
import uz.gita.mobile_banking.domain.usecase.impl.AuthUseCaseImpl
import uz.gita.mobile_banking.domain.usecase.impl.CardUseCaseImpl
import uz.gita.mobile_banking.domain.usecase.impl.StartScreenUseCaseImpl
import uz.gita.mobile_banking.domain.usecase.impl.UserUseCaseImpl

// Created by Jamshid Isoqov on 12/26/2022
@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindStartUseCase(impl: StartScreenUseCaseImpl): StartScreenUseCase

    @Binds
    fun bindAuthUseCase(impl: AuthUseCaseImpl): AuthUseCase

    @Binds
    fun bindUserUseCase(impl: UserUseCaseImpl): UserUseCase

    @Binds
    fun bindCardUseCase(impl: CardUseCaseImpl): CardUseCase

}