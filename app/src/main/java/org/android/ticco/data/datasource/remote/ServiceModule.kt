package org.android.ticco.data.datasource.remote

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.android.ticco.AuthInterceptorClient
import org.android.ticco.NoAuthInterceptorClient
import org.android.ticco.data.datasource.remote.auth.AuthApiService
import org.android.ticco.data.datasource.remote.ticket.TicketApiService
import org.android.ticco.data.datasource.remote.user.UserApiService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideAuthApiService(
        @NoAuthInterceptorClient retrofit: Retrofit): AuthApiService =
        retrofit.create(AuthApiService::class.java)

    @Provides
    @Singleton
    fun provideUserApiService(
        @AuthInterceptorClient retrofit: Retrofit): UserApiService =
        retrofit.create(UserApiService::class.java)

    @Provides
    @Singleton
    fun provideTicketApiService(
        @AuthInterceptorClient retrofit: Retrofit
    ): TicketApiService = retrofit.create(TicketApiService::class.java)
}