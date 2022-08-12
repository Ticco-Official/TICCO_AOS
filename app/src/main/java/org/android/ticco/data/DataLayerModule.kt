package org.android.ticco.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.android.ticco.data.datasource.remote.auth.AuthRemoteDataSourceImpl
import org.android.ticco.data.datasource.remote.ticket.TicketRemoteDataSourceImpl
import org.android.ticco.data.datasource.remote.user.UserRemoteDataSourceImpl
import org.android.ticco.data.repository.AuthRepositoryImpl
import org.android.ticco.data.repository.TicketRepositoryImpl
import org.android.ticco.data.repository.UserRepositoryImpl
import org.android.ticco.domain.datasource.remote.AuthRemoteDataSource
import org.android.ticco.domain.datasource.remote.TicketRemoteDataSource
import org.android.ticco.domain.datasource.remote.UserRemoteDataSource
import org.android.ticco.domain.repository.AuthRepository
import org.android.ticco.domain.repository.TicketRepository
import org.android.ticco.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataLayerModule {

    @Binds
    @Singleton
    abstract fun bindsAuthRepository(repositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindsAuthDataSource(dataSourceImpl: AuthRemoteDataSourceImpl): AuthRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsUserRepository(repository: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindsUserDataSource(dataSourceImpl: UserRemoteDataSourceImpl): UserRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsTicketDataSource(dataSourceImpl: TicketRemoteDataSourceImpl): TicketRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsTicketRepository(repositoryImpl: TicketRepositoryImpl): TicketRepository
}