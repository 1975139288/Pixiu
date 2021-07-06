package com.poker.pixiu.app.bean

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/6/15 18:26
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * FROM user")
    fun query(): User?
}