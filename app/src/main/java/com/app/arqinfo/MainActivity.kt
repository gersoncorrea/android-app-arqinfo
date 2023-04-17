package com.app.arqinfo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.app.core.actions.FeatureActions
import com.newrelic.agent.android.NewRelic

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NewRelic.withApplicationToken("AA5ee0b2171ab32d89a15cd9848a931e18ed3fd8bc-NRMA")
            .start(this.applicationContext)
        startActivity(Intent(FeatureActions.AUTH_ACTION))
        finish()
    }

    override fun onResume() {
        super.onResume()
        Log.d("MAIN", "Resume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MAIN", "Pause")
    }
}
