package com.app.feature.authentication.login

import com.app.feature.authentication.remote.LoginBottom
import com.app.feature.authentication.remote.LoginHeader

interface LoginContract {
    interface View {
        fun bindHeader(header: LoginHeader)

        fun bindBottom(loginBottom: LoginBottom)

        fun bindForgotPassword(forgotPassword: String)
    }
}