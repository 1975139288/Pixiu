package com.poker.pixiu

import com.google.gson.annotations.SerializedName

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/7 16:46
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
data class Banner(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)


data class HotKeyList(
    val `data`: List<Data>
) {
    data class Data(
        val id: Int,
        val link: String,
        val name: String,
        val order: Int,
        val visible: Int
    )
}

data class LoginRequest(
    val username: String,
    val password: String
)
