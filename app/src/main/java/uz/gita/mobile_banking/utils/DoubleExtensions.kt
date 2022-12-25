package uz.gita.mobile_banking.utils

// Created by Jamshid Isoqov on 12/17/2022
fun Double.getFinanceType(): String {
    return "$this".combine("sum")
}