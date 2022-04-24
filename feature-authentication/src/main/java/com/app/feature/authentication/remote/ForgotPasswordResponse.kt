package com.app.feature.authentication.remote

import com.app.feature.authentication.domain.ForgotPasswordModel

data class ForgotPasswordResponse(
    val img: String,
    val label: LabelsDefaultVO,
    val button: String
)

fun ForgotPasswordResponse.toModel() = ForgotPasswordModel(
    img = img,
    label = label.mapToModel(),
    button = button
)