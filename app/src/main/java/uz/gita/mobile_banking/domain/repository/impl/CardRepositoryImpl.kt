package uz.gita.mobile_banking.domain.repository.impl

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.mobile_banking.data.remote.api.CardApi
import uz.gita.mobile_banking.data.remote.request.card.CardDto
import uz.gita.mobile_banking.data.remote.request.card.UpdateCardDto
import uz.gita.mobile_banking.data.remote.response.card.CardData
import uz.gita.mobile_banking.data.remote.response.user.MessageData
import uz.gita.mobile_banking.domain.repository.CardRepository
import uz.gita.mobile_banking.utils.ResultData
import uz.gita.mobile_banking.utils.func
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val cardApi: CardApi,
    private val gson: Gson
) : CardRepository {
    override fun getAllCards(): Flow<ResultData<List<CardData>>> =
        flow {
            emit(cardApi.getAllCards().func(gson))
        }.flowOn(Dispatchers.IO)

    override fun addCard(cardDto: CardDto): Flow<ResultData<MessageData>> =
        flow {
            emit(cardApi.addCard(cardDto).func(gson))
        }.flowOn(Dispatchers.IO)

    override fun updateCard(updateCardDto: UpdateCardDto): Flow<ResultData<MessageData>> =
        flow {
            emit(cardApi.updateCard(updateCardDto).func(gson))
        }.flowOn(Dispatchers.IO)

    override suspend fun deleteCard(cardId: Int) {
        cardApi.deleteCard(cardId)
    }
}