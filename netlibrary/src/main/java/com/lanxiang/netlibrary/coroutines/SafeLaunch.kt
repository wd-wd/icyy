package com.lanxiang.netlibrary.coroutines

import android.util.Log
import android.widget.Toast
import androidx.annotation.RestrictTo
import com.google.gson.JsonParseException
import com.lanxiang.netlibrary.ApiException
import com.lanxiang.netlibrary.BaseDTO
import com.lanxiang.netlibrary.BuildConfig
import kotlinx.coroutines.*
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * @auhthor: wangdong
 * @e-mail: wangdong@happygo.com
 * @date: 2021/4/28 8:56 AM
 * @desc:方法
 *
 */

/**
 * 异常逻辑处理块
 */
typealias CatchBlock = (Throwable) -> Unit
/**
 * 正常逻辑处理块
 */
typealias NormalBlock = suspend CoroutineScope.() -> Unit

fun CoroutineScope.safeLaunch(config: CoroutineScopeConfig.() -> Unit): Job {

    return CoroutineScopeConfig(this)
        .also(config)
        .launch()
}

class CoroutineScopeConfig(val scope: CoroutineScope) {
    private val globalExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            Log.e(
                "SafeLaunch",
                "查找协程异常，报错信息位子SafeLaunch class line 36:${throwable.message}"
            )
        }
    lateinit var tryBlock: NormalBlock
    private var catchBlock: CatchBlock? = null
    private var isToastError: Boolean = true

    fun tryBlock(tryBlock: NormalBlock) {
        this.tryBlock = tryBlock
    }

    fun catchError(catchBlock: CatchBlock) {
        this.catchBlock = catchBlock
    }

    fun launch(): Job {
        return scope.launch(globalExceptionHandler) {
            try {
                tryBlock()
            } catch (e: Throwable) {
                if (e !is CancellationException) {
                    handleException(convertException(e), catchBlock)
                } else {
                    throw e
                }
            } finally {

            }
        }
    }

    private fun handleException(e: Throwable, catchBlock: CatchBlock?) {
        if (e is ApiException) {
            if (e.errCode == "401") {
                //登录去
            } else {
                catchBlock?.invoke(e)
            }
        } else {

        }
    }

    private fun convertException(e: Throwable): ApiException {
        return when (e) {
            is JsonParseException
                , is JSONException
                , is ParseException -> ApiException(PARSE_ERROR, ERROR_MSG_PARSE)
            is SocketTimeoutException -> ApiException(ERROR_MSG_TIMEOUT, ERROR_MSG)
            is ConnectException, is HttpException -> ApiException(NETWORK_ERROR, ERROR_MSG_DISCON)
            is UnknownHostException -> ApiException(NETWORK_ERROR, ERROR_MSG)
            is ApiException -> e
            else -> {
                if (BuildConfig.DEBUG) {
                    ApiException(UNKNOWN, e.message)
                } else {
                    ApiException(UNKNOWN, ERROR_MSG_UNKNOWN)
                }
            }
        }
    }
}

fun <T> BaseDTO<T>.result(): T? {
    if (isSuccess) {
        return data
    } else {
        throw ApiException(code, message)
    }
}