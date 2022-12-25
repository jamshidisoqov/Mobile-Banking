package uz.gita.mobile_banking.utils

import com.google.gson.Gson
import retrofit2.Response
import uz.gita.mobile_banking.data.remote.response.user.MessageData

// Created by Jamshid Isoqov on 12/25/2022

fun <T> Response<T>.func(gson: Gson): ResultData<T> {
    val data = this
    if (data.isSuccessful) {
        return if (data.body() != null) {
            val body = data.body()!!
            ResultData.Success(body)
        } else {
            ResultData.Error(Throwable("Body null"))
        }
    }
    val messageData = gson.fromJson(errorBody()!!.string(), MessageData::class.java)
    return ResultData.Error(
        Throwable(message = messageData.message)
    )
}