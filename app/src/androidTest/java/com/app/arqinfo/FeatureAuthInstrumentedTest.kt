package com.app.arqinfo

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.app.feature.authentication.FeatureAuthenticationActivity
import com.app.feature.authentication.remote.AuthApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class FeatureAuthInstrumentedTest {

    private lateinit var mockClient: OkHttpClient
    private lateinit var mockWebServer: MockWebServer

    @get:Rule
    val activityRule = ActivityScenarioRule(FeatureAuthenticationActivity::class.java)

    @Before
    fun setup() {
        mockClient = Mockito.mock(OkHttpClient::class.java)
        mockWebServer = MockWebServer()
    }

    @Test
    fun testMockLoginScreen() = runBlocking {
        Dispatchers.Main

        mockWebServer.start()

        val mockServerUrl = mockWebServer.url("/arqinfo/login")

    }

    @Test
    fun testVisibility() {
        onView(withId(R.id.fragmentLogin)).check(matches(isCompletelyDisplayed()))
        activityRule.scenario.close()
    }

    @Test
    fun checkInput() {
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

        activityRule.scenario.close()
    }

    private fun readJsonFileFromAssets(fileName: String): String {
        val context = InstrumentationRegistry.getInstrumentation().context
        val inputStream = context.assets.open(fileName)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        inputStream.close()
        return jsonString
    }
}
