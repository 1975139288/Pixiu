package com.poker.common.mvvm

import kotlinx.coroutines.Job

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/6 15:22
 * @Desc: 定义UI层的行为
 * @GitHub：https://github.com/pokerfaceCmy
 */
open class BaseActionEvent

class ShowLoadingEvent(val job: Job?) : BaseActionEvent()

class ShowLoadingWithoutJobEvent : BaseActionEvent()

class ShowToastEvent(val message: String) : BaseActionEvent()

class DismissLoadingEvent : BaseActionEvent()

class LoginAndFinishEvent : BaseActionEvent()