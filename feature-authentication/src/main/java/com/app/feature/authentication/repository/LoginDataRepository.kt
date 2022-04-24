package com.app.feature.authentication.repository

import com.app.core.network.NetworkCall
import com.app.feature.authentication.domain.LoginRepository
import com.app.feature.authentication.remote.AuthApi
import com.app.feature.authentication.remote.mapToModel
import com.app.feature.authentication.remote.toModel

class LoginDataRepository(
    private val api: AuthApi
) : LoginRepository {
    override suspend fun getLogin() = NetworkCall.safeApiCall { api.getLoginAsync().mapToModel() }

    override suspend fun getForgotPassword() =
        NetworkCall.safeApiCall { api.getForgotPasswordAsync().toModel() }
}
