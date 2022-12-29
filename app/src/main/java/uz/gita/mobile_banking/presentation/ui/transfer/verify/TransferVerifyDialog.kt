package uz.gita.mobile_banking.presentation.ui.transfer.verify

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.fraggjkee.smsconfirmationview.SmsConfirmationView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
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
class TransferVerifyDialog(
    private val cardData: CardData,
    private val recipientName: String,
    private val recipientCardNumber: String,
    private val amount: String,
    private val token: String
) : BottomSheetDialogFragment(R.layout.screen_transfer_verify) {

    private val viewBinding: ScreenTransferVerifyBinding by viewBinding()

    private var transferClickListener: (() -> Unit)? = null

    private var isCompletedSms: Boolean = false

    private var isFinishedTime: Boolean = false

    private val viewModel: TransferVerifyViewModel by viewModels<TransferVerifyViewModelImpl>()

    fun setTransferClickListener(block: () -> Unit) {
        transferClickListener = block
    }

    private var verifyToken: String = ""

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = viewBinding.include {

        verifyToken = token

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

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
            transferClickListener?.invoke()
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