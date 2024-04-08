package com.guru.data_common.repository

import com.guru.data_common.local.User
import com.guru.data_common.local.UserDao
import com.guru.data_common.remote.UserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val userService: UserService
) : UserRepository {

    override fun getUsers(): Flow<List<User>> {
        return userDao.getAllUsers()
            .map { userList ->
                userList.map { user ->
                    User(
                        id = user.id,
                        name = user.name,
                        image_url = user.image_url
                    )
                }
            }
            .catch { exception ->
                if (exception is IOException) {
                    // Handle the exception by fetching data from the network
                    val usersFromNetwork = userService.getUsers()
                    userDao.insertUsers(usersFromNetwork)
                    emit(usersFromNetwork.map { user ->
                        User(
                            id = user.id,
                            name = user.name,
                            image_url = user.image_url
                        )
                    })
                } else {
                    throw exception
                }
            }
    }

    override suspend fun refreshUsers() {
        val users = userService.getUsers()
        userDao.insertUsers(users)
    }
}
