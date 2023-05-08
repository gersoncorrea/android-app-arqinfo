package com.app.arqinfo

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testVisibility(){
        onView(withId(R.id.fragmentLogin)).check(matches(isCompletelyDisplayed()))
        activityRule.scenario.close()

    }

    @Test
    fun checkInput(){

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
}
