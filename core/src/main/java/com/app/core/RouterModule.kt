package com.app.core

import org.koin.dsl.module

object RouterModule {
    val routerAppModule = module {
        factory<FeatureRouter> { StandardFeatureRouter() }
    }
}