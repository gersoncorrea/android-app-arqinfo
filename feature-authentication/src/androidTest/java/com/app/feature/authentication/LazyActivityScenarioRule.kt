package com.app.feature.authentication

import android.app.Activity
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import org.junit.rules.ExternalResource

class LazyActivityScenarioRule<A : Activity> : ExternalResource {

    private var launchActivity: Boolean
    private var scenarioLaunched: Boolean = false
    private var scenario: ActivityScenario<A>? = null
    private var scenarioSupplier: () -> ActivityScenario<A>

    constructor(launchActivity: Boolean, startActivityClass: Class<A>) {
        this.launchActivity = launchActivity
        scenarioSupplier = { ActivityScenario.launch(startActivityClass) }
    }

    constructor(launchActivity: Boolean, startActivityIntent: Intent) {
        this.launchActivity = launchActivity
        scenarioSupplier = { ActivityScenario.launch(startActivityIntent) }
    }

    fun launch(newIntent: Intent? = null) {
        if (scenarioLaunched) throw IllegalStateException("Scenario has already been launched")
        newIntent?.let { scenarioSupplier = { ActivityScenario.launch(it) } }

        scenario = scenarioSupplier()
        scenarioLaunched = true
    }

    fun getScenario(): ActivityScenario<A> = checkNotNull(scenario)

    inline fun <reified A : Activity> lazyActivityScenarioRule(
        launchActivity: Boolean = true,
        intent: Intent? = null
    ): LazyActivityScenarioRule<A> = if (intent == null) {
        LazyActivityScenarioRule(launchActivity, A::class.java)
    } else {
        LazyActivityScenarioRule(launchActivity, intent)
    }
}
