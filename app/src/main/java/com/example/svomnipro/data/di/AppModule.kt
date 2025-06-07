package com.example.svomnipro.data.di

import android.content.Context
import com.example.svomnipro.data.repository.LoginRepositoryImpl
import com.example.svomnipro.data.source.RestDataSource
import com.example.svomnipro.domain.repository.LoginRepository
import com.example.svomnipro.domain.useCase.auth.AuthUseCase
import com.example.svomnipro.domain.useCase.auth.GetCharacterById
import com.example.svomnipro.domain.useCase.auth.GetCharacters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun providerAuthUseCase(
        loginRepository: LoginRepository,
        @ApplicationContext context: Context
    ): AuthUseCase {
        return AuthUseCase(
            getCharacters = GetCharacters(
                repository = loginRepository
            ),
            getCharacterById = GetCharacterById(
                repository = loginRepository
            )
        )
    }

    @Provides
    @Singleton
    fun provideLoginRepository(
        resDataSource: RestDataSource
    ): LoginRepository {
        return LoginRepositoryImpl(
            resDataSource = resDataSource
        )
    }
}
