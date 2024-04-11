package com.guru.domain_common.repository

import com.guru.domain_common.model.UserDM
import kotlinx.coroutines.flow.Flow

interface UserDomainRepository {
    suspend fun getUsers(isRemote: Boolean): Flow<List<UserDM>>
    suspend fun refreshUsers()
}