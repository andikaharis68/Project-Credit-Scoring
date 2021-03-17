package com.andika.project_credit_scoring.di.module

import com.andika.project_credit_scoring.api.AccountApi
import com.andika.project_credit_scoring.api.HistoryApi
import com.andika.project_credit_scoring.api.LoginApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Singleton
    @Provides
    fun provideAuthApi(retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

    @Singleton
    @Provides
    fun provideAccount(retrofit: Retrofit): AccountApi {
        return retrofit.create(AccountApi::class.java)
    }

    @Singleton
    @Provides
    fun provideHistory(retrofit: Retrofit): HistoryApi {
        return retrofit.create(HistoryApi::class.java)
    }
}