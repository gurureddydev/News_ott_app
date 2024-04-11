package com.guru.domain_common.usecase

import com.guru.domain_common.repository.UserDomainRepository

class RefreshUsersUseCaseImpl(private val userRepository: UserDomainRepository) :
    RefreshUsersUseCase {
    override suspend fun invoke() {
        userRepository.refreshUsers()
    }
}