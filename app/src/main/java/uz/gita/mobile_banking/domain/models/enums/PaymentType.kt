package uz.gita.mobile_banking.domain.models.enums

import uz.gita.mobile_banking.R

// Created by Jamshid Isoqov on 1/5/2023
enum class PaymentType(val icon: Int) {
    NETWORK(R.drawable.ic_phone_payment),
    RESTAURANTS(R.drawable.ic_food_payment),
    MARKET(R.drawable.ic_shop_payment),
    MEDICINE(R.drawable.ic_injection_payment),
    PROVIDERS(R.drawable.ic_providers_payment),
    EDUCATION(R.drawable.ic_education_payment),
    TRANSPORT(R.drawable.ic_transport_payment),
    TV(R.drawable.ic_tv_payment),
    BUDGET(R.drawable.ic_budget_payment),
    SPORT(R.drawable.ic_sport_payment),
    TOURISM(R.drawable.ic_tourism_payment),
    INSURANCE(R.drawable.ic_insurance_payment),
    GAMES(R.drawable.ic_game_payment),
    MAIL(R.drawable.ic_mail_payment),
    OTHERS(R.drawable.ic_others_payment)

}