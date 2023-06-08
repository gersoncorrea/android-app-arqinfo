package com.app.arqinfo.base

import com.google.gson.GsonBuilder
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

inline fun <reified T> configureNetworkForInstrumentationTest(baseTestApi: String) = module {
    single {
        Retrofit.Builder()
            .baseUrl(baseTestApi)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().create(),
                ),
            )
            .build()
    }
    factory { get<Retrofit>().create(T::class.java) }
}
