package uz.gita.mobile_banking.presentation.ui.home.pages.accounts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks
import uz.gita.mobile_banking.R
import uz.gita.mobile_banking.databinding.ScreenAccountsBinding
import uz.gita.mobile_banking.presentation.presenter.AccountScreenViewModelImpl
import uz.gita.mobile_banking.presentation.ui.home.pages.accounts.adapter.CardAdapter
import uz.gita.mobile_banking.presentation.ui.home.pages.accounts.adapter.LastTransfersAdapter
import uz.gita.mobile_banking.utils.*

// Created by Jamshid Isoqov on 12/24/2022
@AndroidEntryPoint
class AccountsScreen : Fragment(R.layout.screen_accounts) {

    private val viewBinding: ScreenAccountsBinding by viewBinding()

    private val viewModel: AccountScreenViewModel by viewModels<AccountScreenViewModelImpl>()

    private val lastTransfersAdapter: LastTransfersAdapter by lazy(LazyThreadSafetyMode.NONE) {
        LastTransfersAdapter()
    }

    private val cardAdapter: CardAdapter by lazy(LazyThreadSafetyMode.NONE) {
        CardAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = viewBinding.include {
        listCards.set3DItem(true)
        listCards.adapter = cardAdapter

        listLastTransfers.adapter = lastTransfersAdapter

        viewModel.loadingSharedFlow.onEach {
            if (it) {
                shimmerFrame.shimmerFrameHome.apply {
                    startShimmer()
                    visible()
                }
                containerHome.inVisible()

            } else {
                containerHome.visible()
                shimmerFrame.shimmerFrameHome.apply {
                    stopShimmer()
                    gone()
                }
            }
        }.launchIn(lifecycleScope)

        viewModel.messageSharedFlow.onEach {
            showMessageDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.errorSharedFlow.onEach {
            showErrorDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.cardsList.onEach {
            cardAdapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.lastTransactions.onEach {
            lastTransfersAdapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        imageNotification.clicks()
            .onEach {
                viewModel.navigateToNotification()
            }.launchIn(lifecycleScope)
        cardAddCard.clicks()
            .onEach {
                viewModel.navigateToAddCard()
            }.launchIn(lifecycleScope)
        cardTransfer.clicks()
            .onEach {
                viewModel.navigateToSend()
            }.launchIn(lifecycleScope)

        cardPay.clicks()
            .onEach {
                viewModel.navigateToPayment()
            }.launchIn(lifecycleScope)

        cardMore.clicks()
            .onEach {
                viewModel.clickMore()
            }.launchIn(lifecycleScope)
        imageSearch.clicks()
            .onEach {
                viewModel.searchLastTransfers()
            }.launchIn(lifecycleScope)

        cardAdapter.setItemClick {
            viewModel.navigateToCardDetails(it)
        }
        lastTransfersAdapter.setItemClickListener {
            viewModel.navigateToLastTransfersDetail(it)
        }
        viewModel.getCards()

        viewModel.getLastTransfers()
    }
}