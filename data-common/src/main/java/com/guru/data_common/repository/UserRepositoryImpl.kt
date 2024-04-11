package com.guru.data_common.repository

import com.guru.data_common.local.UserDao
import com.guru.data_common.remote.UserService
import com.guru.domain_common.model.UserDM
import com.guru.domain_common.repository.UserDomainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import toListUserDM
import toListUserEntity
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val userService: UserService
) : UserDomainRepository {

    override suspend fun getUsers(isRemote: Boolean): Flow<List<UserDM>> {
        return if (!isRemote) {
            userDao.getAllUsers().map { it.toListUserDM() }
        } else {
            val usersFromNetwork = userService.getUsers()
            userDao.insertUsers(usersFromNetwork.toListUserEntity())
            userDao.getAllUsers().map { it.toListUserDM() }
        }
    }

     override suspend fun refreshUsers() {
        val users = userService.getUsers()
        userDao.insertUsers(users.toListUserEntity())
    }
}
