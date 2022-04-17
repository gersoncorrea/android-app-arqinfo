package com.app.feature.authentication.repository

import com.app.core.network.NetworkCall
import com.app.feature.authentication.domain.LoginRepository
import com.app.feature.authentication.remote.AuthApi

class LoginDataRepository(
    private val api: AuthApi
) : LoginRepository {
    override suspend fun getLogin() = NetworkCall.safeApiCall { api.getLoginAsync() }

}