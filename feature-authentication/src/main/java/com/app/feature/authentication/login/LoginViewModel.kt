package com.app.feature.authentication.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.app.core.network.ApiResult
import com.app.core.network.Resource
import com.app.feature.authentication.domain.LoginModel
import com.app.feature.authentication.domain.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository,
) : ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(Main + viewModelJob)

    private val _login = MutableLiveData<LoginModel>()
    val login: LiveData<LoginModel> get() = _login

    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> get() = _error

    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> get() = _loading

    suspend fun getModel(): Resource<LoginModel> {
        return repository.getLoginScreen()
    }

    val teste: LiveData<Resource<LoginModel>> = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        emit(repository.getLoginScreen())
    }

    fun getAll() {
        _loading.value = true
        viewModelScope.launch {
            when (val apiResponse = repository.getLogin()) {
                is ApiResult.Success -> {
                    apiResponse.data.let {
                        _loading.value = false
                        _login.value = it
                    }
                }

                is ApiResult.Error -> {
                    _loading.value = false
                    _error.value = apiResponse.exception
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
