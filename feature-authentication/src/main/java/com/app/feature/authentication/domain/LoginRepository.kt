package com.app.feature.authentication.domain

import com.app.core.network.ApiResult
import com.app.core.network.Resource

interface LoginRepository {
    suspend fun getLogin(): ApiResult<LoginModel>
    suspend fun getLoginScreen(): Resource<LoginModel>



    suspend fun getForgotPassword(): ApiResult<ForgotPasswordModel>

    suspend fun getSignUp(): ApiResult<SignUpModel>
}