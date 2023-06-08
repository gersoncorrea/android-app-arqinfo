package com.app.arqinfo

import android.app.Application
import com.app.core.RouterModule
import com.app.core.network.NetworkModule
import com.app.feature.authentication.remote.AuthApi
import okhttp3.OkHttpClient
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TestApplication : Application() {



    override fun onCreate() {
        super.onCreate()
        startKoin {
            loadKoinModules(RouterModule.routerAppModule)
        }
    }

//    private inline fun <reified T> createTestWebService(okHttpClient: OkHttpClient): T {
//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://localhost:$MOCK_WEB_SERVER_PORT/")
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        return retrofit.create(T::class.java)
//    }
//
    companion object {
        const val MOCK_WEB_SERVER_PORT = 4007
    }
}