package org.android.ticco.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.android.ticco.data.datasource.remote.auth.AuthRemoteDataSourceImpl
import org.android.ticco.data.repository.AuthRepositoryImpl
import org.android.ticco.domain.datasource.remote.AuthRemoteDataSource
import org.android.ticco.domain.repository.AuthRepository
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
}