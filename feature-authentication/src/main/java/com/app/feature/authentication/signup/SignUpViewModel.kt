package com.app.feature.authentication.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.core.network.ApiResult
import com.app.feature.authentication.domain.LoginRepository
import com.app.feature.authentication.domain.SignUpModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val repository: LoginRepository
) : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _signup = MutableLiveData<SignUpModel>()
    val signup: LiveData<SignUpModel> get() = _signup

    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> get() = _error

    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> get() = _loading

    fun getSignUpScreen() {
        _loading.value = true
        viewModelScope.launch {
            when (val apiResponse = repository.getSignUp()) {

                is ApiResult.Loading->{}

                is ApiResult.Success -> {
                    apiResponse.data.let {
                        _loading.value = false
                        _signup.value = it
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
