package com.app.core

import android.app.Activity
import android.content.Context
import android.content.Intent

interface FeatureRouter {
    fun start(
        receiver: Activity,
        action: String
    )
}

internal class StandardFeatureRouter : FeatureRouter {
    override fun start(receiver: Activity, action: String) {
        receiver.run {
            startActivity(createIntent(receiver, action))
            finish()
        }
    }

    private fun createIntent(context: Context, action: String): Intent {
        return Intent(action).setPackage(context.packageName)
    }
}