package com.poker.common.network.converter

import com.poker.common.mvvm.model.IApiResult
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.internal.Util.jsonAnnotations
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/7 14:44
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
class RetroMoshiConverterFactory<ApiResultType : IApiResult<*>>
private constructor(
    private val moshi: Moshi,
    private val apiClass: Class<ApiResultType>
) : Converter.Factory() {

    companion object {
        fun <ApiResultType : IApiResult<*>> create(
            moshi: Moshi,
            apiClass: Class<ApiResultType>
        ): RetroMoshiConverterFactory<ApiResultType> {
            return RetroMoshiConverterFactory(moshi, apiClass)
        }

    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        return RetroMoshiResponseBodyConverter<Any,ApiResultType>(moshi,type,apiClass)
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<out Annotation>,
        methodAnnotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody> {
        val adapter: JsonAdapter<Any> = moshi.adapter(type, jsonAnnotations(parameterAnnotations))
        return RetroMoshiRequestBodyConverter(adapter)
    }


}