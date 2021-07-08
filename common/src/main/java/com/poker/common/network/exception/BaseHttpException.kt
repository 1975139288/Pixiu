package com.poker.common.network.exception

import java.io.IOException

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/7 14:29
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
open class BaseHttpException constructor(
    val errCode: Int,
    val errMsg: String,
) : IOException(errMsg)