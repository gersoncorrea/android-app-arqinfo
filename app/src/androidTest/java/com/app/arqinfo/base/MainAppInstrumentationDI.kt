package com.app.arqinfo.base

inline fun <reified T> generateTestAppComponent(baseApi: String) =
    listOf(
        configureNetworkForInstrumentationTest<T>(baseApi),
        MockWebServerInstrumentationTest,
    )
