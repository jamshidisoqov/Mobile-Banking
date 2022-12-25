package uz.gita.mobile_banking.data.mapper.user

import uz.gita.mobile_banking.data.local.models.transfer.LastTransferEntity
import uz.gita.mobile_banking.data.local.models.user.BalanceEntity
import uz.gita.mobile_banking.data.local.models.user.MessageEntity
import uz.gita.mobile_banking.data.local.models.user.ProfileEntity
import uz.gita.mobile_banking.data.local.models.user.UserEntity
import uz.gita.mobile_banking.data.remote.response.user.*

// Created by Jamshid Isoqov on 12/25/2022

fun BalanceData.toBalanceEntity(): BalanceEntity {
    return BalanceEntity(totalBalance = totalBalance)
}

fun LastTransferData.toLastTransferEntity(): LastTransferEntity {
    return LastTransferEntity(
        amount = amount,
        from = from,
        time = time,
        to = to,
        type = type
    )
}

fun MessageData.toMessageEntity(): MessageEntity {
    return MessageEntity(message = message)
}

fun ProfileData.toProfileEntity(): ProfileEntity {
    return ProfileEntity(
        firstName = firstName,
        genderType = genderType,
        age = age
    )
}

fun UserData.toUserEntity(): UserEntity {
    return UserEntity(
        phone = phone,
        firstName = firstName,
        lastName = lastName,
        bornDate = bornDate,
        gender = gender
    )
}