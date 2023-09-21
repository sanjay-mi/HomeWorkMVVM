package com.imaginato.homeworkmvvm.data.remote.login

import com.imaginato.homeworkmvvm.data.local.login.Login
import com.imaginato.homeworkmvvm.data.local.login.LoginDao
import com.imaginato.homeworkmvvm.data.remote.base.APIUtils.ERROR_CODE
import com.imaginato.homeworkmvvm.data.remote.base.APIUtils.HEADER_KEY_IMEI
import com.imaginato.homeworkmvvm.data.remote.base.APIUtils.HEADER_KEY_IMEI_VALUE
import com.imaginato.homeworkmvvm.data.remote.base.APIUtils.HEADER_KEY_IMSI
import com.imaginato.homeworkmvvm.data.remote.base.APIUtils.HEADER_KEY_IMSI_VALUE
import com.imaginato.homeworkmvvm.data.remote.base.APIUtils.HEADER_X_ACC
import com.imaginato.homeworkmvvm.data.remote.base.APIUtils.INTERNAL_SERVER_ERROR
import com.imaginato.homeworkmvvm.data.remote.base.APIUtils.SUCCESS_CODE
import com.imaginato.homeworkmvvm.data.remote.login.request.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginDataRepository constructor(
    private var api: LoginApi, private var loginDao: LoginDao
) : LoginRepository {
    override fun doLogin(request: LoginRequest) = flow {
        val headerMap = hashMapOf(
            HEADER_KEY_IMSI to HEADER_KEY_IMSI_VALUE, HEADER_KEY_IMEI to HEADER_KEY_IMEI_VALUE
        )

        val response = api.doLogin(headerMap, request).await()
        response.body()?.let { body ->
            when (body.responseCode) {
                SUCCESS_CODE -> {
                    var xAcc: String
                    response.headers()[HEADER_X_ACC].let { value ->
                        xAcc = if (value.isNullOrBlank()) "" else value
                    }
                    body.responseData?.apply {
                        val login = Login(
                            userId ?: "", userName ?: "", xAcc, isDeleted ?: false
                        )
                        loginDao.insertLoginData(login)
                    }
                    emit(body.message)
                }

                ERROR_CODE -> {
                    emit(body.message)
                }
            }
        } ?: emit(INTERNAL_SERVER_ERROR)
    }.flowOn(Dispatchers.IO)
}