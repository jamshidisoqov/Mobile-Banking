package uz.gita.mobile_banking.data.mapper.auth

import uz.gita.mobile_banking.data.local.models.auth.HeaderTokenEntity
import uz.gita.mobile_banking.data.local.models.auth.TokenEntity
import uz.gita.mobile_banking.data.remote.response.auth.HeaderData
import uz.gita.mobile_banking.data.remote.response.auth.TokenData

// Created by Jamshid Isoqov on 12/25/2022

fun HeaderData.toHeaderEntity(): HeaderTokenEntity {
    return HeaderTokenEntity(
        refreshToken = refreshToken,
        accessToken = accessToken
    )
}

fun TokenData.toTokenEntity(): TokenEntity {
    return TokenEntity(token = token)
}
