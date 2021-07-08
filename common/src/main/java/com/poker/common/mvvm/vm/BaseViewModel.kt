package com.poker.common.mvvm.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poker.common.mvvm.*
import com.poker.common.network.callback.RequestCallback
import com.poker.common.network.exception.BaseHttpException
import com.poker.common.network.exception.ServerException
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.HashMap

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/6 14:54
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
open class BaseViewModel : ViewModel(), IViewModelActionEvent {
    override val showLoadingEventLD: MutableLiveData<ShowLoadingEvent> =
        MutableLiveData<ShowLoadingEvent>()
    override val showLoadingWithoutJobEventLD: MutableLiveData<ShowLoadingWithoutJobEvent> =
        MutableLiveData<ShowLoadingWithoutJobEvent>()
    override val dismissLoadingEventLD: MutableLiveData<DismissLoadingEvent> =
        MutableLiveData<DismissLoadingEvent>()
    override val showToastEventLD: MutableLiveData<ShowToastEvent> =
        MutableLiveData<ShowToastEvent>()
    override val lifecycleSupportedScope: CoroutineScope
        get() = viewModelScope

    fun <Data> enqueue(
        apiFun: suspend () -> Data,
        showLoading: Boolean = true,
        showErrorMsg: Boolean = true,
        callbackFun: (RequestCallback<Data>.() -> Unit)? = null
    ): Job {
        return lifecycleSupportedScope.launch {
            val callback = if (callbackFun == null) null else RequestCallback<Data>().apply {
                callbackFun.invoke(this)
            }
            try {
                callback?.onStart?.invoke()
                if (showLoading) showLoading(coroutineContext[Job])
                val response: Data?
                try {
                    response = apiFun.invoke()
                    onGetResponse(callback, response)
                } catch (ex: Exception) {
                    handleException(showErrorMsg, ex, callback)
                    return@launch
                }
            } finally {
                callback?.onFinally?.invoke()
            }
        }
    }

    private fun <Data> handleException(
        showErrorMsg: Boolean,
        ex: Exception,
        callback: RequestCallback<Data>?
    ) {
        callback?.let {
            if (ex is CancellationException) {
                callback.onCancelled?.invoke()
            }
            val exception = generateException(showErrorMsg, ex)
            callback.onFailed?.invoke(exception)
        }
    }

    private fun generateException(showErrorMsg: Boolean, ex: Exception): BaseHttpException {
        ex.printStackTrace()
        if (showErrorMsg) showToast(ex.message.toString())

        return when (ex) {
            is CancellationException -> {
                BaseHttpException(404, "请求已取消")
            }
            is ServerException -> {
                if (showErrorMsg) {
                    showToast(ex.errMsg)
                }
                BaseHttpException(ex.errCode, ex.errMsg)
            }
            else -> {
                BaseHttpException(400, "未知错误")
            }
        }
    }

    private suspend fun <Data> onGetResponse(callback: RequestCallback<Data>?, httpData: Data?) {
        callback?.let {
            withContext(NonCancellable) {
                callback.onSuccess?.let {
                    withContext(Dispatchers.Main) {
                        it.invoke(httpData)
                    }
                }
                callback.onSuccessIO?.let {
                    withContext(Dispatchers.IO) {
                        it.invoke(httpData)
                    }
                }
            }
        }
    }
}