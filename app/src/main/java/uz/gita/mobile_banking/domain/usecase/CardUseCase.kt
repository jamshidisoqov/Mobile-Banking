package uz.gita.mobile_banking.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.mobile_banking.data.remote.request.card.CardDto
import uz.gita.mobile_banking.data.remote.request.card.UpdateCardDto
import uz.gita.mobile_banking.data.remote.response.card.CardData
import uz.gita.mobile_banking.data.remote.response.user.MessageData
import uz.gita.mobile_banking.utils.ResultData

// Created by Jamshid Isoqov on 12/25/2022
interface CardUseCase {

    fun getAllCards(): Flow<ResultData<List<CardData>>>

    fun addCard(cardDto: CardDto): Flow<ResultData<MessageData>>

    fun updateCard(updateCardDto: UpdateCardDto): Flow<ResultData<MessageData>>

    suspend fun deleteCard(cardId: Int)

}