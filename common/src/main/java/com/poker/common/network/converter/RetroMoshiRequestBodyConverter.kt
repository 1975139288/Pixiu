package com.poker.common.network.converter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonWriter
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.Buffer
import retrofit2.Converter

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/7 16:01
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
class RetroMoshiRequestBodyConverter<T> constructor(
    private val adapter: JsonAdapter<T>
) : Converter<T, RequestBody> {

    override fun convert(value: T): RequestBody {
        val buffer = Buffer()
        val writer = JsonWriter.of(buffer)
        adapter.toJson(writer, value)
        return buffer.readByteString().toRequestBody(
            "application/json; charset=UTF-8".toMediaType()
        )
    }

}