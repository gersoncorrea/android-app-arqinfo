package com.app.feature.authentication.login

import com.app.feature.authentication.domain.LoginBottom
import com.app.feature.authentication.domain.LoginHeader

interface LoginContract {
    interface View {
        fun bindHeader(header: LoginHeader)

        fun bindBottom(loginBottom: LoginBottom)

        fun bindForgotPassword(forgotPassword: String)
    }
}