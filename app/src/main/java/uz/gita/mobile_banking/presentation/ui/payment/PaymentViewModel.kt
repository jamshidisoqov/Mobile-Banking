package uz.gita.mobile_banking.presentation.ui.payment

import kotlinx.coroutines.flow.StateFlow
import uz.gita.mobile_banking.domain.models.enums.PaymentDirection
import uz.gita.mobile_banking.domain.models.enums.PaymentType

// Created by Jamshid Isoqov on 1/5/2023
interface PaymentViewModel {

    val paymentTypeFlow: StateFlow<PaymentDirection>

    fun navigateToSearchPayment()

    fun changePaymentType(paymentDirection: PaymentDirection)

    fun navigateToPaymentDetail(paymentType: PaymentType)

}