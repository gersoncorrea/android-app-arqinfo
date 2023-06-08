package com.app.arqinfo

fun generateTestAppComponent(baseApi: String) =
    listOf(
        configureNetworkForInstrumentationTest(baseApi),
        MockWebServerInstrumentationTest
    )