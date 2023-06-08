package com.app.arqinfo.base

import okhttp3.mockwebserver.MockWebServer
import org.koin.dsl.module

val MockWebServerInstrumentationTest = module {
    factory {
        MockWebServer()
    }
}
