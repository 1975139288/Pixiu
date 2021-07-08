package com.poker.pixiu

import com.poker.common.mvvm.vm.BaseViewModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import java.lang.reflect.Type
import javax.inject.Inject




/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/7 16:43
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    val mainClient: MainClient
) : BaseViewModel() {
    @Inject
    lateinit var moshi :Moshi

    fun getBanner() {
        enqueue({ mainClient.getBanner() }) {
            onSuccess {
                val type: Type = Types.newParameterizedType(
                    MutableList::class.java,
                    Banner::class.java
                )
                val adapter: JsonAdapter<MutableList<Banner>> = moshi.adapter(type)
                Timber.d(adapter.toJson(it))
            }
        }
    }

    fun getHotkey() {
        enqueue({ mainClient.getHotkey() }) {
            onSuccess {
                Timber.d(it?.get(0)?.name)
            }
        }
    }

    fun login(username: String, password: String) {
        val map = hashMapOf<String,Any>()
        map["username"] = username
        map["password"] = password
        enqueue({ mainClient.login(map) }) {
            onSuccess {
                Timber.d("onSuccess")
            }
            onFailed {
                Timber.e(it)
            }
        }
    }
}