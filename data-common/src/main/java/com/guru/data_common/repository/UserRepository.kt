package com.guru.data_common.repository

// UserRepository.kt
import com.guru.data_common.local.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<List<UserEntity>>
    suspend fun refreshUsers()
}
