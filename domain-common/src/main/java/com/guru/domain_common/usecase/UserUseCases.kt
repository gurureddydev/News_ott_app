package com.guru.domain_common.usecase


import javax.inject.Inject

class UserUseCases @Inject constructor(
    val getUsersUseCase: GetUsersUseCase,
    val refreshUsersUseCase: RefreshUsersUseCase
)
