package uz.gita.mobile_banking.presentation.ui.transfer.verify

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.fraggjkee.smsconfirmationview.SmsConfirmationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks
import uz.gita.mobile_banking.R
import uz.gita.mobile_banking.data.remote.response.card.CardData
import uz.gita.mobile_banking.databinding.ScreenTransferVerifyBinding
import uz.gita.mobile_banking.presentation.presenter.TransferVerifyViewModelImpl
import uz.gita.mobile_banking.utils.*

// Created by Jamshid Isoqov on 12/29/2022
@AndroidEntryPoint
class TransferVerifyScreen : Fragment(R.layout.screen_transfer_verify) {

    private val viewModel: TransferVerifyViewModel by viewModels<TransferVerifyViewModelImpl>()

    private val viewBinding: ScreenTransferVerifyBinding by viewBinding()

    private val args: TransferVerifyScreenArgs by navArgs()

    private lateinit var cardData: CardData

    private lateinit var recipientName: String

    private lateinit var recipientCardNumber: String

    private lateinit var amount: String

    private var isCompletedSms: Boolean = false

    private var isFinishedTime: Boolean = false

    private var verifyToken: String = ""

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = viewBinding.include {

        verifyToken = args.token

        recipientName = args.recipientName

        recipientCardNumber = args.recipientPan

        cardData = args.cardData

        amount = args.amount.toString()


        viewModel.loadingSharedFlow.onEach {
            if (it) showProgress() else hideProgress()
        }.launchIn(lifecycleScope)

        viewModel.messageSharedFlow.onEach {
            toast(it)
        }.launchIn(lifecycleScope)

        viewModel.errorSharedFlow.onEach {
            toast(it)
        }.launchIn(lifecycleScope)

        containerCard.apply {
            tvBalance.text = cardData.amount.toString()
            tvCardNumber.text = cardData.pan
            tvCardExpireDate.text = "${cardData.expiredMonth}/${cardData.expiredYear}"
        }

        tvAmount.text = amount

        tvCardNumber.text = recipientCardNumber

        tvName.text = recipientName

        viewModel.startTimer.onEach {
            runTimer()
        }.launchIn(lifecycleScope)

        viewModel.backSharedFlow.onEach {
           viewModel.navigateToMain()
        }.launchIn(lifecycleScope)

        viewModel.tokenFlow.onEach {
            verifyToken = it
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        smsChecker.onChangeListener = SmsConfirmationView.OnChangeListener { _, isComplete ->
            isCompletedSms = isComplete
            check()
        }

        tvTimeExpired.clicks().onEach {
            if (isFinishedTime) {
                isFinishedTime = false
                viewModel.resendCode()
                runTimer()
                check()
            }
        }.launchIn(lifecycleScope)

        btnTransfer.clicks()
            .onEach {
                viewModel.verify(verifyToken, smsChecker.enteredCode)
            }.launchIn(lifecycleScope)
    }

    private fun runTimer() = viewBinding.include {
        var time = 180
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            while (time > 0) {
                tvTimeExpired.text = time.getTimeFormat()
                delay(1000)
                time--
            }
            tvTimeExpired.text = resources.getString(R.string.code_resend)
            isFinishedTime = true
            check()
        }
    }

    private fun check() = viewBinding.include {
        btnTransfer.isEnabled = isCompletedSms && !isFinishedTime
    }
}