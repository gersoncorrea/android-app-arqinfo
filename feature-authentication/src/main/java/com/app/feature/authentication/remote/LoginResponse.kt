package com.app.feature.authentication.remote

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("header") val header: LoginHeader,
    @SerializedName("forgotPassword") val forgotPassword: String,
    @SerializedName("bottom") val bottom: LoginBottom
)

data class LoginHeader(
    @SerializedName("img") val img: String,
    @SerializedName("labels") val labels: List<LabelsDefault>
)

data class LabelsDefault(
    @SerializedName("title") val title: String,
    @SerializedName("type") val type: String
)

data class LoginBottom(
    @SerializedName("button") val button: String,
    @SerializedName("signup") val signup: String
)
