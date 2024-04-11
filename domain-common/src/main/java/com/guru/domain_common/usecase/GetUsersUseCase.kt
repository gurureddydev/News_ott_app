package com.guru.domain_common.usecase

import com.guru.domain_common.model.UserDM
import kotlinx.coroutines.flow.Flow

interface GetUsersUseCase {
    suspend operator fun invoke(isRemote: Boolean): Flow<List<UserDM>>
}