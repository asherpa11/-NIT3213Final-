package com.example.nit3213final.di

import com.example.nit3213final.data.repository.AuthRepository
import com.example.nit3213final.data.repository.IAuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(repo: AuthRepository): IAuthRepository
}