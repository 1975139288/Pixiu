package com.poker.pixiu.app

import android.content.Context
import android.view.View
import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import timber.log.Timber

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/7 12:54
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */

fun Context.logInfo(msg: String) {
    Timber.i(msg)
}

fun Context.logDebug(msg: String) {
    Timber.d(msg)
}

fun Context.logError(msg: String) {
    Timber.e(msg)
}

fun Context.logWarn(msg: String) {
    Timber.w(msg)
}

fun Context.logVerbose(msg: String) {
    Timber.v(msg)
}

class BaseBindingViewHolder(private val binding: ViewBinding) : BaseViewHolder(binding.root) {
    constructor(itemView: View) : this(ViewBinding { itemView })

    @Suppress("UNCHECKED_CAST")
    fun <VB : ViewBinding> getViewBinding() = binding as VB

}
