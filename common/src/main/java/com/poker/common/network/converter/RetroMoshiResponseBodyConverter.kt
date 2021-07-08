package com.poker.common.network.converter

import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.poker.common.mvvm.model.IApiResult
import com.poker.common.network.exception.ServerException
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody
import retrofit2.Converter
import java.lang.reflect.Type

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/7 15:10
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
class RetroMoshiResponseBodyConverter<T, ApiResultType : IApiResult<*>> constructor(
    private val moshi: Moshi,
    private val type: Type,
    private val apiClass: Class<ApiResultType>,
) : Converter<ResponseBody, T> {

    @Suppress("UNCHECKED_CAST")
    override fun convert(value: ResponseBody): T? {
        return value.use { v ->
            val response = v.string()
//            val apiResult: ApiResultType? = moshi.adapter(apiClass).fromJson(response)
            val apiResult: ApiResultType = Gson().fromJson(response, apiClass)
            if (apiResult?.isSuccess == false) {
                throw ServerException(apiResult.httpCode, apiResult.httpMsg)
            } else if (type == apiClass) {
                return apiResult as T
            }
            //如果未设置data字段，则认为返回的整个结果就是数据段
            if (TextUtils.isEmpty(apiResult?.dataField)) {
                moshi.adapter(apiClass).fromJson(response) as T
            } else {
                Gson().fromJson(
                    (JsonParser().parse(response) as JsonObject)[apiResult?.dataField],
                    type
                )
            }
        }
    }
}

//class RetroMoshiResponseBodyConverter<T, ApiResultType : IApiResult<*>> constructor(
//    private val type: Type,
//    private val apiClass: Class<ApiResultType>,
//) : Converter<ResponseBody, T> {
//
//    @Suppress("UNCHECKED_CAST")
//    override fun convert(value: ResponseBody): T? {
//        return value.use { v ->
//            val gson = Gson()
//            val response = v.string()
//            val apiResult: ApiResultType = gson.fromJson(response, apiClass)
//            if (!apiResult.isSuccess) {
//                throw ServerException(apiResult.httpCode, apiResult.httpMsg)
//            } else if (type == apiClass) {
//                return apiResult as T
//            }
//            //如果未设置data字段，则认为返回的整个结果就是数据段
//            if (TextUtils.isEmpty(apiResult.dataField)) {
//                gson.fromJson(response, type)
//            }
//            else gson.fromJson(
//                (JsonParser().parse(response) as JsonObject)[apiResult.dataField],
//                type
//            )
//        }
//    }
//}
