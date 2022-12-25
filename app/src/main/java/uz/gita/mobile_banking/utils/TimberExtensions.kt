package uz.gita.mobile_banking.utils

import timber.log.Timber

// Created by Jamshid Isoqov on 12/12/2022


fun log(message: String, tag: String = "TTT") {
    Timber.tag(tag).d(message)
}
