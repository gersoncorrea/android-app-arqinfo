package com.app.feature.authentication

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginFragmentEspressoTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(FeatureAuthenticationActivity::class.java)

    @Test
    fun testVisibility() {
        Espresso.onView(ViewMatchers.withId(R.id.fragmentLogin))
            .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
        activityRule.scenario.close()
    }

    @Test
    fun checkInput() {
        Espresso.onView(ViewMatchers.withId(R.id.editTextTextUserEmail))
            .perform(ViewActions.typeText("gerson@email.com"), ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.editTextTextUserPassword))
            .perform(ViewActions.typeText("123456"), ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.editTextTextUserEmail))
            .check(ViewAssertions.matches(ViewMatchers.withText("gerson@email.com")))

        Espresso.onView(ViewMatchers.withId(R.id.editTextTextUserPassword))
            .check(ViewAssertions.matches(ViewMatchers.withText("123456")))

        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())
//        Espresso.onView(ViewMatchers.withId(R.id.homeActivity))
//            .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))

        activityRule.scenario.close()
    }

}