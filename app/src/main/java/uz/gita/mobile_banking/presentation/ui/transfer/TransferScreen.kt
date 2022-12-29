package uz.gita.mobile_banking.presentation.ui.transfer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks
import ru.ldralighieri.corbind.widget.textChanges
import uz.gita.mobile_banking.R
import uz.gita.mobile_banking.databinding.ScreenTransferBinding
import uz.gita.mobile_banking.presentation.presenter.TransferViewModelImpl
import uz.gita.mobile_banking.presentation.ui.home.pages.accounts.adapter.CardAdapter
import uz.gita.mobile_banking.presentation.ui.transfer.verify.TransferVerifyDialog
import uz.gita.mobile_banking.utils.*

// Created by Jamshid Isoqov on 12/25/2022
@AndroidEntryPoint
class TransferScreen : Fragment(R.layout.screen_transfer) {

    private val viewBinding: ScreenTransferBinding by viewBinding()

    private val viewModel: TransferViewModel by viewModels<TransferViewModelImpl>()

    private var current = 0

    private var boolAmount: Boolean = false

    private var boolSendTo: Boolean = false

    private lateinit var cardAdapter: CardAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = viewBinding.include {

        viewModel.loadingSharedFlow.onEach {
            if (it) showProgress() else hideProgress()
        }.launchIn(lifecycleScope)

        viewModel.messageSharedFlow.onEach {
            showMessageDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.errorSharedFlow.onEach {
            showErrorDialog(it)
        }.launchIn(lifecycleScope)

        inputAmount.addTextChangedListener(MaskWatcherPayment(inputAmount))

        cardAdapter = CardAdapter()

        pagerCards.adapter = cardAdapter

        pagerCards.currentPageCallback {
            current = it
        }

        viewModel.cardFlow.onEach {
            cardAdapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.userFlow.onEach {
            tvIdentifiedUser.text = it
        }.launchIn(lifecycleScope)

        inputAmount.textChanges().onEach {
            boolAmount = it.toString().toDecimalFormat().isNotEmpty()
            check()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        inputSendTo.textChanges().onEach {
            boolSendTo = it.length == 19
            if (boolSendTo) {
                viewModel.getUser(it.toString())
            }
            check()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.openConfirmDialog.onEach {
            val openDialog = TransferVerifyDialog(
                cardData = it.first,
                recipientName = tvIdentifiedUser.text.toString(),
                recipientCardNumber = inputSendTo.text.toString(),
                amount = inputAmount.text.toString(),
                token = it.second
            )
            openDialog.setTransferClickListener {
                findNavController().navigateUp()
            }
            openDialog.show(childFragmentManager, "transfer verify")
        }

        btnTransfer.clicks()
            .onEach {
                val amount = inputAmount.text.toString().toDecimalFormat().toInt()
                val card = inputSendTo.rawText.toString()
                viewModel.transferVerify(amount, card, current)
            }.launchIn(lifecycleScope)

    }

    private fun check() = viewBinding.include {
        btnTransfer.isEnabled = boolSendTo && boolAmount
    }
}