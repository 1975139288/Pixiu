package com.poker.pixiu.app.bean

import com.poker.common.mvvm.model.IApiResult
import com.squareup.moshi.JsonClass

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/7 14:55
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
@JsonClass(generateAdapter = true)
data class DataWrapper<Data>(
    val data: Data,
    val errorCode: Int,
    val errorMsg: String = ""
): IApiResult<Data> {
    override val isSuccess: Boolean
        get() = errorCode == 0

    override val httpData: Data
        get() = data

    override val httpMsg: String
        get() = errorMsg

    override val httpCode: Int
        get() = errorCode

    override val dataField: String
        get() = "data"
}
