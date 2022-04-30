package com.app.feature.authentication.domain

data class SignUpModel(
    val imgHeader: String,
    val labels: List<LabelsDefault>,
    val button: String
)