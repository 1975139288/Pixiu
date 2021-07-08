package com.poker.pixiu

import retrofit2.http.*
import java.util.*
import kotlin.collections.HashMap

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/7 16:45
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
interface ApiService {
    /**
     * 钱包余额
     */
    @GET("banner/json")
    suspend fun getBanner(): MutableList<Banner>

    @GET("hotkey/json")
    suspend fun getHotkey(): HotKeyList

    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(@FieldMap body: HashMap<String,Any>): Any
}