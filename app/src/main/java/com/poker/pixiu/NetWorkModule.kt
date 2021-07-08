package com.poker.pixiu

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/7 16:51
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {
    @Provides
    @Singleton
    fun provideMainClient(apiService: ApiService): MainClient {
        return MainClient(apiService)
    }

    @Provides
    @Singleton
    fun provideMainService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}