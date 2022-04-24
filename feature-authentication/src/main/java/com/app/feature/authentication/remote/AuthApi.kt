package com.app.feature.authentication.remote

import retrofit2.http.GET

interface AuthApi {

    companion object {
        const val BASE = "/arqinfo"
        const val LOGIN = "$BASE/login"
        const val FORGOTPASSWORD = "$BASE/forgotPassword"
    }

    @GET(LOGIN)
    suspend fun getLoginAsync(): LoginResponse

    @GET(FORGOTPASSWORD)
    suspend fun getForgotPasswordAsync(): ForgotPasswordResponse
}