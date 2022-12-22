package uz.gita.mobile_banking.data.remote.authenticator

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

// Created by Jamshid Isoqov on 12/22/2022
class TokenAuthenticator @Inject constructor(

) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val token = getUpdateToken()
        return response.request().newBuilder()
            .header("Authorization", getUpdateToken())
            .build()
    }

    private fun getUpdateToken(): String {
        return ""
    }

}