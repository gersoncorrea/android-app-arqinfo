package com.app.feature.authentication.remote

import com.app.feature.authentication.domain.LoginRepository
import com.app.feature.authentication.forgotpassword.ForgotPasswordViewModel
import com.app.feature.authentication.login.LoginViewModel
import com.app.feature.authentication.repository.LoginDataRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object FeatAuthModule {

    val networkModule = module {
        factory {
            get<Retrofit>().create(AuthApi::class.java)
        }
    }

    val repositoryModule = module {
        single<LoginRepository> { LoginDataRepository(get()) }
    }

    val uiModule = module {
        viewModel { LoginViewModel(repository = get()) }
        viewModel { ForgotPasswordViewModel(repository = get()) }
    }
}