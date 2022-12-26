package uz.gita.mobile_banking.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.mobile_banking.directions.SplashScreenDirection
import uz.gita.mobile_banking.directions.impl.SplashScreenDirectionsImpl

// Created by Jamshid Isoqov on 12/26/2022
@Module
@InstallIn(ViewModelComponent::class)
interface DirectionsModule {

    @Binds
    fun bindSplashScreen(impl: SplashScreenDirectionsImpl): SplashScreenDirection

}