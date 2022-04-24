package com.app.feature.authentication.remote

import com.app.feature.authentication.domain.LabelsDefault
import com.app.feature.authentication.domain.LoginBottom
import com.app.feature.authentication.domain.LoginHeader
import com.app.feature.authentication.domain.LoginModel
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("header") val header: LoginHeaderVO,
    @SerializedName("forgotPassword") val forgotPassword: String,
    @SerializedName("bottom") val bottom: LoginBottomVO
)

fun LoginResponse.mapToModel() = LoginModel(
    header = header.mapToModel(),
    forgotPassword = forgotPassword,
    bottom = bottom.mapToModel()
)


data class LoginHeaderVO(
    @SerializedName("img") val img: String,
    @SerializedName("labelUserInput") val labelUserInput: LabelsDefaultVO,
    @SerializedName("labelPasswordInput") val labelPasswordInput: LabelsDefaultVO
) {
    fun mapToModel() = LoginHeader(
        img = img,
        labelUserInput = labelUserInput.mapToModel(),
        labelPasswordInput = labelPasswordInput.mapToModel()
    )
}

data class LabelsDefaultVO(
    @SerializedName("title") val title: String,
    @SerializedName("type") val type: String
)

fun LabelsDefaultVO.mapToModel() = LabelsDefault(
    title = title,
    type = type
)


data class LoginBottomVO(
    @SerializedName("button") val button: String,
    @SerializedName("signup") val signup: String
)

fun LoginBottomVO.mapToModel() = LoginBottom(
    button = button,
    signup = signup
)

