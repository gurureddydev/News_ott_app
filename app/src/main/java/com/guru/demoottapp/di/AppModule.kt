package com.guru.demoottapp.di

import android.app.Application
import android.content.Context
import com.guru.data_common.local.UserDao
import com.guru.data_common.local.UserDatabase
import com.guru.data_common.remote.RetrofitClient
import com.guru.data_common.remote.UserService
import com.guru.data_common.repository.UserRepository
import com.guru.data_common.repository.UserRepositoryImpl
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
    ): UserRepository {
        return UserRepositoryImpl(userDao, userService)
    }
}
