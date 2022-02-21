package com.app.core.network

import com.app.core.provider.NetworkConfigProvider
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface NetworkModule {
    private fun createConnection(
        client: OkHttpClient,
        networkConfigProvider: NetworkConfigProvider
    ) = Retrofit.Builder()
        .baseUrl(networkConfigProvider.getBaseUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private fun createClient() = OkHttpClient.Builder()
}