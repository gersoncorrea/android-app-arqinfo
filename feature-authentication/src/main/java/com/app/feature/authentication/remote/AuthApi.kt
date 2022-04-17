package com.app.feature.authentication.remote

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface AuthApi {

    companion object {
        const val BASE = "/arqinfo"
        const val LOGIN = "$BASE/login"
    }

    @GET(LOGIN)
    suspend fun getLoginAsync(): LoginResponse
}