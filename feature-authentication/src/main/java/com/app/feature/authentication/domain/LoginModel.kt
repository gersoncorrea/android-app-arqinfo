package com.app.feature.authentication.domain

data class LoginModel(
    val header: LoginHeader,
    val forgotPassword: String,
    val bottom: LoginBottom
)

data class LoginHeader(
    val img: String,
    val labels: List<LabelsDefault>
)

data class LabelsDefault(
    val title: String,
    val type: String
)

data class LoginBottom(
    val button: String,
    val signup: String
)