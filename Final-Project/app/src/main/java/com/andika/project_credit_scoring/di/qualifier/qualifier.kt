package com.andika.project_credit_scoring.di.qualifier

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PostAuth

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ServiceAccount

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GetProfile

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PostRegistration

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GetNewTaskWaiting