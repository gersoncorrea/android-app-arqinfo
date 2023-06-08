package com.app.feature.authentication.domain

import com.app.core.network.ApiResult

interface LoginRepository {
    suspend fun getLogin(): ApiResult<LoginModel>

    suspend fun getForgotPassword(): ApiResult<ForgotPasswordModel>

    suspend fun getSignUp(): ApiResult<SignUpModel>
}