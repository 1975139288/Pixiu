package com.poker.pixiu.app

import androidx.room.Database
import androidx.room.RoomDatabase
import com.poker.pixiu.app.bean.User
import com.poker.pixiu.app.bean.UserDao

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/6/15 18:22
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
@Database(
    entities = [
        User::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}