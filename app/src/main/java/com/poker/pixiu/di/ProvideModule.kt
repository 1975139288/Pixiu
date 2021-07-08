package com.poker.pixiu.di

import android.content.Context
import androidx.room.Room
import com.poker.common.network.converter.RetroMoshiConverterFactory
import com.poker.pixiu.app.AppDatabase
import com.poker.pixiu.app.bean.DataWrapper
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/7 13:28
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
private const val BASE_URL = "https://www.wanandroid.com"

//过期时间 单位秒
private const val TIMEOUT = 30L

//数据库名称
private const val DATABASE_NAME = "PIXIU.db"


@Module
@InstallIn(SingletonComponent::class)
object JsonModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

}

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
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(RetroMoshiConverterFactory.create(moshi, DataWrapper::class.java))
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
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