package com.imaginato.homeworkmvvm.data.remote.login

import com.imaginato.homeworkmvvm.data.remote.base.ApiResponse
import com.imaginato.homeworkmvvm.data.remote.login.request.LoginRequest
import com.imaginato.homeworkmvvm.data.remote.login.response.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface LoginApi {

    @POST("/api/login")
    fun doLogin(
        @HeaderMap
        header: HashMap<String, String>,
        @Body body: LoginRequest
    ): Deferred<Response<ApiResponse<LoginResponse>>>
}