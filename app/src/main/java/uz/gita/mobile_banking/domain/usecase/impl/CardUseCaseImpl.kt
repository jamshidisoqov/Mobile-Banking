package uz.gita.mobile_banking.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.mobile_banking.data.remote.request.card.CardDto
import uz.gita.mobile_banking.data.remote.request.card.UpdateCardDto
import uz.gita.mobile_banking.data.remote.response.card.CardData
import uz.gita.mobile_banking.data.remote.response.user.MessageData
import uz.gita.mobile_banking.domain.repository.CardRepository
import uz.gita.mobile_banking.domain.usecase.CardUseCase
import uz.gita.mobile_banking.utils.ResultData
import javax.inject.Inject

class CardUseCaseImpl @Inject constructor(
    private val cardRepository: CardRepository
) : CardUseCase {
    override fun getAllCards(): Flow<ResultData<List<CardData>>> = cardRepository.getAllCards()

    override fun addCard(cardDto: CardDto): Flow<ResultData<MessageData>> =
        cardRepository.addCard(cardDto)

    override fun updateCard(updateCardDto: UpdateCardDto): Flow<ResultData<MessageData>> =
        cardRepository.updateCard(updateCardDto)

    override suspend fun deleteCard(cardId: Int) = cardRepository.deleteCard(cardId)
}