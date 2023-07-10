package com.app.feature.authentication.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.app.core.network.ApiResult
import com.app.feature.authentication.domain.LoginModel
import com.app.feature.authentication.domain.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.SupervisorJob

class LoginViewModel(
    private val repository: LoginRepository,
) : ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(Main + viewModelJob)

    fun getScreenData(): LiveData<ApiResult<LoginModel>> = liveData(viewModelScope.coroutineContext) {
        emit(ApiResult.Loading())
        emit(repository.getLogin())
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
