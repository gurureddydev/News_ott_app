package com.guru.demoottapp.di

import android.app.Application
import android.content.Context
import com.guru.data_common.local.UserDao
import com.guru.data_common.local.UserDatabase
import com.guru.data_common.remote.RetrofitClient
import com.guru.data_common.remote.UserService
import com.guru.data_common.repository.UserRepositoryImpl
import com.guru.domain_common.repository.UserDomainRepository
import com.guru.domain_common.usecase.GetUsersUseCase
import com.guru.domain_common.usecase.GetUsersUseCaseImpl
import com.guru.domain_common.usecase.RefreshUsersUseCase
import com.guru.domain_common.usecase.RefreshUsersUseCaseImpl
import com.guru.domain_common.usecase.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideUserDatabase(context: Context): UserDatabase {
        return UserDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.userDao()
    }

    @Provides
    @Singleton
    fun provideUserService(): UserService {
        return RetrofitClient.userService
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        userDao: UserDao,
        userService: UserService
    ): UserDomainRepository {
        return UserRepositoryImpl(userDao, userService)
    }

    @Provides
    @Singleton
    fun provideGetUsersUseCase(userRepository: UserDomainRepository): GetUsersUseCase {
        return GetUsersUseCaseImpl(userRepository)
    }

    @Provides
    @Singleton
    fun provideRefreshUsersUseCase(userRepository: UserDomainRepository): RefreshUsersUseCase {
        return RefreshUsersUseCaseImpl(userRepository)
    }

    @Provides
    @Singleton
    fun provideUserUseCases(
        getUsersUseCase: GetUsersUseCase,
        refreshUsersUseCase: RefreshUsersUseCase
    ): UserUseCases {
        return UserUseCases(getUsersUseCase, refreshUsersUseCase)
    }
}
