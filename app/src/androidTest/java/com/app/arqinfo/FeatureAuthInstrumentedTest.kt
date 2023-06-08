package com.app.arqinfo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.arqinfo.base.BaseUITest
import com.app.arqinfo.base.generateTestAppComponent
import com.app.feature.authentication.FeatureAuthenticationActivity
import com.app.feature.authentication.remote.AuthApi
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules

@RunWith(AndroidJUnit4::class)
class FeatureAuthInstrumentedTest : BaseUITest() {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        super.server()
        loadKoinModules(generateTestAppComponent<AuthApi>(getMockWebServerUrl()).toMutableList())
    }

    fun provideBaseOkHttp(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        return builder.build()
    }

    @Test
    fun shouldGoToHomeActivityIfUserInputIsCorrect() {
        val activityRule = ActivityScenario.launch(FeatureAuthenticationActivity::class.java)

        mockResponseTest("login.screen.json", "/arqinfo/login")

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
}
