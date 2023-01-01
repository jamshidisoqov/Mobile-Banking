package uz.gita.mobile_banking.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.mobile_banking.directions.*
import uz.gita.mobile_banking.directions.impl.*

// Created by Jamshid Isoqov on 12/26/2022
@Module
@InstallIn(ViewModelComponent::class)
interface DirectionsModule {

    @Binds
    fun bindSplashScreen(impl: SplashScreenDirectionsImpl): SplashScreenDirection

    @Binds
    fun bindLoginScreen(impl: LoginScreenDirectionImpl): LoginScreenDirection

    @Binds
    fun bindRegisterScreen(impl: RegisterScreenDirectionImpl): RegisterScreenDirection

    @Binds
    fun bindLoginVerifyScreen(impl: LoginVerifyScreenDirectionImpl): LoginVerifyScreenDirection

    @Binds
    fun bindRegisterVerifyScreen(impl: RegisterVerifyScreenDirectionImpl): RegisterVerifyScreenDirection

    @Binds
    fun bindPinScreen(impl: PinScreenDirectionImpl): PinScreenDirection

    @Binds
    fun bindAccountScreen(impl: AccountScreenDirectionImpl): AccountScreenDirection

    @Binds
    fun bindTransferScreen(impl: TransferScreenDirectionImp): TransferScreenDirection

    @Binds
    fun bindTransferVerifyScreen(impl: TransferVerifyScreenDirectionImpl): TransferVerifyScreenDirection

    @Binds
    fun bindMenuDialog(impl: MenuDialogDirectionImpl):MenuDialogDirection
}