package com.guru.domain_common.usecase

import com.guru.domain_common.model.UserDM
import com.guru.domain_common.repository.UserDomainRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface GetUsersUseCase {
    suspend operator fun invoke(isRemote: Boolean): Flow<List<UserDM>>
}

class GetUsersUseCaseImpl(
    private val userRepository: UserDomainRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : GetUsersUseCase {
    override suspend fun invoke(isRemote: Boolean): Flow<List<UserDM>> {
        return withContext(dispatcher) {
            userRepository.getUsers(isRemote)
        }
    }
}

