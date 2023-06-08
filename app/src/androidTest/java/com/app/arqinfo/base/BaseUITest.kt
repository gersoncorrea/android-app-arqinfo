package com.app.arqinfo.base

import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.koin.test.KoinTest

open class BaseUITest : KoinTest {
    lateinit var mockWebServer: MockWebServer

    @Before
    open fun server() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    fun mockResponseTest(fileName: String, endpoint: String) {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    endpoint -> MockResponse().setResponseCode(200).setBody(getJson(fileName))
                    else -> MockResponse().setResponseCode(400)
                }
            }
        }
    }

    fun getMockWebServerUrl() = mockWebServer.url("/").toString()

    private fun getJson(fileName: String): String {
        val context = InstrumentationRegistry.getInstrumentation().context
        val inputStream = context.assets.open(fileName)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        inputStream.close()
        return jsonString
    }
}
