package com.guru.domain_common.usecase

import com.guru.domain_common.model.UserDM
import com.guru.domain_common.repository.UserDomainRepository
import kotlinx.coroutines.flow.Flow

class GetUsersUseCaseImpl(private val userRepository: UserDomainRepository) : GetUsersUseCase {
    override suspend fun invoke(isRemote: Boolean): Flow<List<UserDM>> {
        return userRepository.getUsers(isRemote)
    }
}