package uz.gita.mobile_banking.presentation.ui.payment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mobile_banking.R
import uz.gita.mobile_banking.databinding.ScreenPaymentsBinding
import uz.gita.mobile_banking.domain.models.enums.PaymentDirection
import uz.gita.mobile_banking.domain.models.enums.PaymentType
import uz.gita.mobile_banking.presentation.presenter.PaymentViewModelImpl
import uz.gita.mobile_banking.presentation.ui.payment.adapters.PaymentGridAdapter
import uz.gita.mobile_banking.presentation.ui.payment.adapters.PaymentLinearAdapter
import uz.gita.mobile_banking.utils.include
import uz.gita.mobile_banking.utils.log

// Created by Jamshid Isoqov on 1/5/2023
@AndroidEntryPoint
class PaymentsScreen : Fragment(R.layout.screen_payments) {

    private val viewBinding: ScreenPaymentsBinding by viewBinding()

    private val paymentLinearAdapter: PaymentLinearAdapter by lazy(LazyThreadSafetyMode.NONE) {
        PaymentLinearAdapter()
    }

    private val paymentGridAdapter: PaymentGridAdapter by lazy(LazyThreadSafetyMode.NONE) {
        PaymentGridAdapter()
    }

    private var paymentDirection: Int = 0

    private val viewModel: PaymentViewModel by viewModels<PaymentViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = viewBinding.include {
        viewModel.paymentTypeFlow.onEach {
            paymentDirection = it.ordinal
            val adapter = when (it) {
                PaymentDirection.LINEAR -> {
                    imagePaymentType.setImageResource(R.drawable.ic_linear)
                    listPayments.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                    paymentLinearAdapter
                }
                PaymentDirection.GRID -> {
                    imagePaymentType.setImageResource(R.drawable.ic_grid)
                    listPayments.layoutManager = GridLayoutManager(requireContext(),3)
                    paymentGridAdapter
                }
            }
            listPayments.adapter = adapter
            adapter.submitList(PaymentType.values().toList())
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        paymentGridAdapter.setItemClickListener {
            log("Grid")
            viewModel.navigateToPaymentDetail(it)
        }

        paymentLinearAdapter.setItemClickListener {
            log("Linear")
            viewModel.navigateToPaymentDetail(it)
        }

        imagePaymentType.setOnClickListener {
            viewModel.changePaymentType(PaymentDirection.values()[1 - paymentDirection])
        }
    }
}