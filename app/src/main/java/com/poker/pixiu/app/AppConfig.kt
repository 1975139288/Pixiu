package com.poker.pixiu.app

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/6/15 17:37
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */

private const val BASE_URL = ""

//过期时间 单位秒
private const val TIMEOUT = 60L

//数据库名称
private const val DATABASE_NAME = "PIXIU.db"

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addNetworkInterceptor(logInterceptor)
            .retryOnConnectionFailure(true)
            .hostnameVerifier { _, _ -> true }
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideAppDataBase(
        @ApplicationContext applicationContext: Context,
    ): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}