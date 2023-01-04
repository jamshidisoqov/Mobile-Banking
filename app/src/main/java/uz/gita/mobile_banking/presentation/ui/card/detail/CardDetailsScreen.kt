package uz.gita.mobile_banking.presentation.ui.card.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mobile_banking.R
import uz.gita.mobile_banking.databinding.ScreenCardDetailBinding
import uz.gita.mobile_banking.presentation.presenter.CardDetailViewModelImpl
import uz.gita.mobile_banking.presentation.ui.history.adapter.HistoryAdapter
import uz.gita.mobile_banking.utils.*

// Created by Jamshid Isoqov on 1/4/2023
@AndroidEntryPoint
class CardDetailsScreen : Fragment(R.layout.screen_card_detail) {

    private val viewBinding: ScreenCardDetailBinding by viewBinding()

    private val viewModel: CardDetailViewModel by viewModels<CardDetailViewModelImpl>()

    private val adapter: HistoryAdapter by lazy(LazyThreadSafetyMode.NONE) {
        HistoryAdapter()
    }

    private val args: CardDetailsScreenArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = viewBinding.include {

        viewModel.loadingSharedFlow.onEach {
            if (it) showProgress() else hideProgress()
        }.launchIn(lifecycleScope)

        viewModel.errorSharedFlow.onEach {
            showErrorDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.messageSharedFlow.onEach {
            showMessageDialog(it)
        }.launchIn(lifecycleScope)

        val cardData = args.cardData
        listTransfers.adapter = adapter

        tvOwner.text = cardData.owner

        containerCard.include {

            tvCardNumber.text = cardData.pan.getCardNumber()

            tvCardExpireDate.text =
                "${cardData.expiredMonth}".combine("/").combine("${cardData.expiredYear}")

            tvBalance.text = cardData.amount.toDouble().getFinanceType()

        }
        viewModel.getTransfersByCard(cardData)
    }


}