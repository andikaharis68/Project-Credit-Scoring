package com.andika.project_credit_scoring.di

import com.andika.project_credit_scoring.repositories.LoginRepository
import com.andika.project_credit_scoring.repositories.LoginRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class RepoModule {

    @Binds
    abstract fun bindRepositoryPostAuth(repositoryImpl: LoginRepositoryImpl): LoginRepository

}

