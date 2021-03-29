package com.andika.project_credit_scoring.di.module

import com.andika.project_credit_scoring.di.qualifier.PostAuth
import com.andika.project_credit_scoring.di.qualifier.ServiceAccount
import com.andika.project_credit_scoring.repositories.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class RepoModule {

    @Binds
    @PostAuth
    abstract fun bindRepositoryPostAuth(repositoryImpl: LoginRepositoryImpl): LoginRepository

    @Binds
    @ServiceAccount
    abstract fun bindRepositoryAccount(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository

    @Binds
    abstract fun bindRepositoryHistory(historyRepositoryImpl: HistoryRepositoryImpl): HistoryRepository

    @Binds
    abstract fun bindRepositoryTransaction(transactionRepositoryImpl: TransactionRepositoryImpl): TransactionRepository

    @Binds
    abstract fun bindRepositoryRole(roleRepositoryImpl: RoleRepositoryImpl): RoleRepository

}

