package com.app.feature_sample

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.core.RouterModule
import com.app.feature.authentication.FeatureAuthenticationActivity
import com.app.feature.authentication.R
import com.app.feature.authentication.remote.FeatAuthModule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules

@RunWith(AndroidJUnit4::class)
class LoginFragmentEspressoTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(FeatureAuthenticationActivity::class.java)

    @Before
    fun startKoin() {
        loadKoinModules(
            listOf(
                RouterModule.routerAppModule,
                FeatAuthModule.networkModule,
                FeatAuthModule.repositoryModule,
                FeatAuthModule.uiModule
            )
        )
    }

    @Test
    fun check_if_login_fragment_is_show() {
        activityRule.scenario.onActivity {
            onView(withId(R.id.userEmailInputLayout))
                .perform(
                    typeText("Teste")
                )
        }
    }
}
