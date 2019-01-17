package com.infullmobile.androidhomework.presentation.weather.exception

import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import okhttp3.ResponseBody
import org.json.JSONObject
import java.io.IOException
import java.net.SocketTimeoutException

class CustomExceptions {

    companion object {
        private const val CONNECTION_TO_LONG = "Connection take to long"
        private const val CONNECTION_ERROR = "Connection error"
        private const val UNEXPECTED_ERROR = "Unexpected error"
    }

    fun getException(throwable: Throwable): String {
        return if (throwable is HttpException) {
            val retrofitResponseBody = throwable.response().errorBody()
            getRetrofitErrorMessage(retrofitResponseBody)
        } else when (throwable) {
            is SocketTimeoutException -> CONNECTION_TO_LONG
            is IOException -> CONNECTION_ERROR
            else -> UNEXPECTED_ERROR
        }
    }

    @Suppress("TooGenericExceptionCaught")
    private fun getRetrofitErrorMessage(responseBody: ResponseBody?): String {
        return try {
            var error = responseBody?.string()
            try {
                val jsonObject = JSONObject(error)
                error = jsonObject.getString("message")
            } catch (e: Exception) {
                Log.e("CustomException", "Error of Custom Retrofit Error")
            }
            error ?: UNEXPECTED_ERROR
        } catch (e: Exception) {
            e.message.toString()
        }
    }
}
