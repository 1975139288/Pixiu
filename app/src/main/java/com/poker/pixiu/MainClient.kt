package com.poker.pixiu

import javax.inject.Inject

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/7 16:50
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
class MainClient @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getBanner() = apiService.getBanner()

    suspend fun getHotkey() = apiService.getHotkey().data

    suspend fun login(body: HashMap<String, Any>) = apiService.login(body)
}