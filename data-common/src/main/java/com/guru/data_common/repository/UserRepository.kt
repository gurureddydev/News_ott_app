package com.guru.data_common.repository

// UserRepository.kt
import com.guru.data_common.local.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<List<User>>
    suspend fun refreshUsers()
}
