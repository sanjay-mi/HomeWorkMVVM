package com.imaginato.homeworkmvvm.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.imaginato.homeworkmvvm.data.remote.login.LoginRepository
import com.imaginato.homeworkmvvm.data.remote.login.request.LoginRequest
import com.imaginato.homeworkmvvm.ui.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class MainActivityViewModel(private val repository: LoginRepository) : BaseViewModel() {

    private val _loginValidationEvent = MutableLiveData<LoginValidation>()
    val loginValidationEvent: LiveData<LoginValidation>
        get() {
            return _loginValidationEvent
        }

    private var _progress: MutableLiveData<Boolean> = MutableLiveData()
    val progress: LiveData<Boolean>
        get() {
            return _progress
        }

    private var _resultLiveData: MutableLiveData<String> = MutableLiveData()
    val loginResultLiveData: LiveData<String>
        get() {
            return _resultLiveData
        }

    /**
     * Login call
     * */
    fun doLogin(userName: String, password: String) {
        viewModelScope.launch {
            val loginRequest = LoginRequest(userName, password)
            repository.doLogin(loginRequest).onStart {
                    _progress.value = true
                }.catch {
                    _progress.value = false
                }.onCompletion {}.collect {
                    _progress.value = false
                    _resultLiveData.value = it
                }
        }
    }

    /**
     * Check validation
     */
    fun checkLoginValidation(userName: String, password: String) {
        when {
            userName.isEmpty() -> {
                _loginValidationEvent.value = LoginValidation.IS_USERNAME_BLANK
            }

            password.isEmpty() -> {
                _loginValidationEvent.value = LoginValidation.IS_PASSWORD_BLANK
            }

            else -> {
                _loginValidationEvent.value = LoginValidation.VALID_INPUT
            }
        }
    }
}