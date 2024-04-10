package com.guru.data_common.repository

import UserDomainRepository
import com.guru.data_common.local.UserEntity
import com.guru.data_common.local.UserDao
import com.guru.data_common.remote.UserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import model.UserDM
import toListUserDM
import toListUserEntity
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val userService: UserService
) : UserDomainRepository {

    override suspend fun getUsers(isRemote:Boolean): Flow<List<UserDM>>  = flow {
       if(!isRemote) {
           try {
               userDao.getAllUsers().map { it.toListUserDM() }
           }catch (e:Exception){
               val usersFromNetwork = userService.getUsers()
               userDao.insertUsers(usersFromNetwork.toListUserEntity())
               userDao.getAllUsers().map { it.toListUserDM() }
           }
       }else {
            userService.getUsers()
       }
    }

     suspend fun refreshUsers() {
        val users = userService.getUsers()
        userDao.insertUsers(users.toListUserEntity())
    }
}
