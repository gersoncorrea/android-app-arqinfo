package com.app.feature.authentication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.app.core.RouterModule
import com.app.feature.authentication.login.LoginFragment
import com.app.feature.authentication.remote.FeatAuthModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class FeatureAuthenticationActivity : AppCompatActivity(R.layout.feature_auth_activity) {
    private val appModules by lazy {
        listOf(
            RouterModule.routerAppModule,
            FeatAuthModule.networkModule,
            FeatAuthModule.repositoryModule,
            FeatAuthModule.uiModule
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules( appModules )
        savedInstanceState?.isEmpty.apply {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.frame_view, LoginFragment())
            }
        }
    }
}
