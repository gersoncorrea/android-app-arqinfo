package com.app.feature.authentication.login

import com.app.feature.authentication.domain.LabelsDefault
import com.app.feature.authentication.domain.LoginBottom
import com.app.feature.authentication.domain.LoginHeader
import com.app.feature.authentication.domain.LoginModel

object TestObjects {

    val loginObject = LoginModel(
        header = LoginHeader(
            img = "imglink",
            labelUserInput = LabelsDefault(
                title = "title",
                type = "type"
            ),
            labelPasswordInput = LabelsDefault(
                title = "title",
                type = "type"
            ),
        ),
        forgotPassword = "forgotPassword",
        bottom = LoginBottom(
            button = "button",
            signup = "signup"
        )
    )
}
