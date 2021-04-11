package com.andika.project_credit_scoring.di.qualifier

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PostAuth

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ServiceAccount
