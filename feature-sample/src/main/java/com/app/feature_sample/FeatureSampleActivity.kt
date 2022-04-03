package com.app.feature_sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.core.FeatureRouter
import com.app.core.actions.FeatureActions.AUTH_ACTION
import org.koin.android.ext.android.inject

class FeatureSampleActivity : AppCompatActivity() {
    private val featureRouter: FeatureRouter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        featureRouter.start(this, AUTH_ACTION)
    }
}