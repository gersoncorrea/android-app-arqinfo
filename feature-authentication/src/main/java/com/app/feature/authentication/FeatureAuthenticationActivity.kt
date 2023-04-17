package com.app.feature.authentication

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.app.core.RouterModule
import com.app.feature.authentication.databinding.FeatureAuthActivityBinding
import com.app.feature.authentication.login.LoginFragment
import com.app.feature.authentication.remote.FeatAuthModule
import org.koin.core.context.loadKoinModules

class FeatureAuthenticationActivity : AppCompatActivity() {

    private val binding by lazy {
        FeatureAuthActivityBinding.inflate(layoutInflater)
    }

    private val appModules by lazy {
        listOf(
            RouterModule.routerAppModule,
            FeatAuthModule.networkModule,
            FeatAuthModule.repositoryModule,
            FeatAuthModule.uiModule,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(appModules)
        setSupportActionBar(binding.customToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        savedInstanceState?.isEmpty.apply {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(binding.frameView.id, LoginFragment())
            }
        }
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        Log.d("AUTH", "Resume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("AUTH", "Pause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("AUTH", "Destroy")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                supportFragmentManager.popBackStack()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
