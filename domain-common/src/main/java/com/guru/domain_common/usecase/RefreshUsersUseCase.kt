package com.guru.domain_common.usecase

import com.guru.domain_common.repository.UserDomainRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface RefreshUsersUseCase {
    suspend operator fun invoke()
}

class RefreshUsersUseCaseImpl(
    private val userRepository: UserDomainRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : RefreshUsersUseCase {
    override suspend fun invoke() {
        withContext(dispatcher) {
            userRepository.refreshUsers()
        }
    }
}
