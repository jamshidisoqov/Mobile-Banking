package uz.gita.mobile_banking.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.mobile_banking.domain.usecase.StartScreenUseCase
import uz.gita.mobile_banking.domain.usecase.impl.StartScreenUseCaseImpl

// Created by Jamshid Isoqov on 12/26/2022
@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindStartScreenUseCase(impl: StartScreenUseCaseImpl): StartScreenUseCase

}