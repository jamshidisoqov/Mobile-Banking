package uz.gita.mobile_banking.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.mobile_banking.domain.repository.*
import uz.gita.mobile_banking.domain.repository.impl.*
import javax.inject.Singleton

// Created by Jamshid Isoqov on 12/25/2022
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @[Binds Singleton]
    fun bindCardRepository(impl: CardRepositoryImpl): CardRepository

    @[Binds Singleton]
    fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @[Binds Singleton]
    fun bindTransferRepository(imp:TransferRepositoryImpl):TransferRepository

    @[Binds Singleton]
    fun bindExchangeRepository(impl:ExchangeRepositoryImpl):ExchangeRepository
}