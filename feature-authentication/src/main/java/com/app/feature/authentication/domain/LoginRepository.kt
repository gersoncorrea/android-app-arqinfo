package com.app.feature.authentication.domain

import com.app.core.network.ApiResult
import com.app.feature.authentication.remote.LoginResponse

interface LoginRepository {
    suspend fun getLogin(): ApiResult<LoginResponse>
}