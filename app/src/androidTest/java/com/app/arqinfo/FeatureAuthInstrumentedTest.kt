package com.app.arqinfo

import android.os.SystemClock
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.app.feature.authentication.FeatureAuthenticationActivity
import com.app.feature.authentication.domain.LoginModel
import com.app.feature.authentication.remote.AuthApi
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4::class)
class FeatureAuthInstrumentedTest : BaseUITest() {

    //    private lateinit var mockWebServer: MockWebServer
    private val mockLogin = MutableLiveData<LoginModel>()

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

//    @get:Rule
//    val activityRule = ActivityScenarioRule(FeatureAuthenticationActivity::class.java)

    @Before
    fun setup() {
        super.server()
        loadKoinModules(generateTestAppComponent(getMockWebServerUrl()).toMutableList())
//        mockWebServer = MockWebServer()
//        mockWebServer.start(TestApplication.MOCK_WEB_SERVER_PORT)

    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    fun provideBaseOkHttp(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        return builder.build()
    }


    @Test
    fun testMockLoginScreen() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(readJsonFileFromAssets("login.screen.json"))
        )

//        launchFragmentInContainer {  }
        mockLogin.postValue(readJsonFileFromAssets("login.screen.json") as LoginModel)
    }

    @Test
    fun testVisibility() {

        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse().setResponseCode(200)
                    .setBody(readJsonFileFromAssets("login.screen.json"))
                    .throttleBody(1024, 5, TimeUnit.SECONDS)
            }

        }

        onView(withId(R.id.fragmentLogin)).check(matches(isCompletelyDisplayed()))
//        activityRule.scenario.close()
    }

    @Test
    fun checkInput() {
        val activityRule = ActivityScenario.launch(FeatureAuthenticationActivity::class.java)

        mockResponseTest("login.screen.json","/arqinfo/login")

        SystemClock.sleep(3000)

        onView(withId(R.id.editTextTextUserEmail))
            .perform(typeText("gerson@email.com"), closeSoftKeyboard())

        onView(withId(R.id.editTextTextUserPassword))
            .perform(typeText("123456"), closeSoftKeyboard())

        onView(withId(R.id.editTextTextUserEmail))
            .check(matches(withText("gerson@email.com")))

        onView(withId(R.id.editTextTextUserPassword))
            .check(matches(withText("123456")))

        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.homeActivity)).check(matches(isCompletelyDisplayed()))

        activityRule.close()
    }

    private fun readJsonFileFromAssets(fileName: String): String {
        val context = InstrumentationRegistry.getInstrumentation().context
        val inputStream = context.assets.open(fileName)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        inputStream.close()
        return jsonString
    }
}
