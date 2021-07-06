package com.poker.pixiu.app.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/6/15 18:22
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
@Entity
data class User(
    //主键永远是1,保证数据库中只有1个用户信息
    @PrimaryKey
    val userId: Long,
    val userName: String = "",
)
