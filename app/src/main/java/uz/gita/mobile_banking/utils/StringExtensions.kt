package uz.gita.mobile_banking.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

// Created by Jamshid Isoqov an 10/12/2022


@SuppressLint("SimpleDateFormat")
fun getCurrentDate(): String {
    val c = Calendar.getInstance().time
    return SimpleDateFormat("MMM dd,yyyy").format(c)
}

@SuppressLint("SimpleDateFormat")
fun getCurrentDate(date: Date): String {
    return SimpleDateFormat("MMM dd,yyyy").format(date)
}

@SuppressLint("SimpleDateFormat")
fun String.toDate(): Date {
    val format = SimpleDateFormat("MMM dd,yyyy")
    format.parse(this)
    return format.calendar.time
}

fun String.combine(s2: String) = "$this $s2"

fun String.getDigitOnly(): Int {
    val builder = StringBuilder()
    for (i in this) {
        if (i.isDigit()) {
            builder.append(i)
        }
    }
    return builder.toString().toInt()
}

fun String.getCardNumber(): String {
    return "************".plus(this)
}

fun String.getOrderString(): String {
    val builder = StringBuilder()
    builder.append(this[0])
    builder.append(this.substring(1).lowercase())
    return builder.toString()
}

//mask watcher with separator
fun String.maskWatcher(size: Int, separator: Char = ' '): String {
    val builder = StringBuilder(this.reversed())
    val formatterString = StringBuilder()
    builder.chunked(size).forEach { formatterString.append(it.plus(separator)) }
    return formatterString.reversed().toString().trim(separator)
}


