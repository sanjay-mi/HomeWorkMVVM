package com.imaginato.homeworkmvvm.data.remote.login

import com.imaginato.homeworkmvvm.data.remote.login.request.LoginRequest
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    fun doLogin(request: LoginRequest): Flow<String>
}