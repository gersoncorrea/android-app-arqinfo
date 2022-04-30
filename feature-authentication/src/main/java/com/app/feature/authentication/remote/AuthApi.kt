package com.app.feature.authentication.remote

import retrofit2.http.GET

interface AuthApi {

    companion object {
        private const val BASE = "/arqinfo"
        const val LOGIN = "$BASE/login"
        const val FORGOT_PASSWORD = "$BASE/forgotPassword"
        const val SIGNUP = "$BASE/signup"
    }

    @GET(LOGIN)
    suspend fun getLoginAsync(): LoginResponse

    @GET(FORGOT_PASSWORD)
    suspend fun getForgotPasswordAsync(): ForgotPasswordResponse

    @GET(SIGNUP)
    suspend fun getSignUpAsync(): SignUpResponse
}