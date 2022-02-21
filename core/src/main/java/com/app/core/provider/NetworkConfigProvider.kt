package com.app.core.provider

interface NetworkConfigProvider {
    fun getBaseUrl(): String
    fun getCdnBaseUrl(): String
}
