package com.app.feature.authentication.remote

import com.app.feature.authentication.domain.SignUpModel
import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("imgHeader") val imgHeader: String,
    @SerializedName("labels") val labels: List<LabelsDefaultVO>,
    @SerializedName("button") val button: String
)

fun SignUpResponse.toModel() = SignUpModel(
    imgHeader = this.imgHeader,
    labels = this.labels.map {
        it.mapToModel()
    },
    button = this.button
)