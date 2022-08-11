package org.android.ticco

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthInterceptorClient


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NoAuthInterceptorClient