package com.app.feature.authentication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.app.core.RouterModule
import com.app.feature.authentication.login.LoginFragment
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class FeatureAuthenticationActivity : AppCompatActivity(R.layout.feature_auth_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(RouterModule.routerAppModule)
        savedInstanceState?.isEmpty.apply {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.frame_view, LoginFragment())
            }
        }
    }
}
