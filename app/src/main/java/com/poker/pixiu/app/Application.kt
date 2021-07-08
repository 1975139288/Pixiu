package com.poker.pixiu.app

import android.app.Application
import com.blankj.utilcode.util.AppUtils
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.poker.pixiu.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree


/**
 * @Author: pokerfaceCmy
 * @Date: 2021/6/15 17:29
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
@HiltAndroidApp
class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        initLog()
    }

    /**
     * 初始化日志模块
     */
    private fun initLog() {
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .methodCount(1)
            .methodOffset(6)        // (Optional) Hides internal method calls up to offset. Default 5
            .tag(AppUtils.getAppName()) // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))

        if (BuildConfig.DEBUG) {
            Timber.plant(object : DebugTree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    Logger.log(priority, tag, message, t)
                }
            })
        }

    }
}