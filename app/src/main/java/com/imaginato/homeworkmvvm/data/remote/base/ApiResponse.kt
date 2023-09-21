package com.imaginato.homeworkmvvm.data.remote.base

import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("errorCode")
    lateinit var responseCode: String

    @SerializedName("errorMessage")
    lateinit var message: String
}

open class ApiResponse<T> : BaseResponse() {
    @SerializedName("data")
    var responseData: T? = null
}

object APIUtils {
    const val HEADER_KEY_IMSI = "IMSI"
    const val HEADER_KEY_IMSI_VALUE = "357175048449937"

    const val HEADER_KEY_IMEI = "IMEI"
    const val HEADER_KEY_IMEI_VALUE = "510110406068589"

    const val SUCCESS_CODE = "00"
    const val ERROR_CODE = "01"

    const val HEADER_X_ACC = "X-Acc"

    const val INTERNAL_SERVER_ERROR = "Internal Server Error"
}
