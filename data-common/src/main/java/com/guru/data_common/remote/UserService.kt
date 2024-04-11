package com.guru.data_common.remote

import com.guru.domain_common.model.UserDM
import retrofit2.http.GET

interface UserService {

    @GET("v3/bcf458ff-af78-4958-b18b-0a1e381d819b")
    suspend fun getUsers(): List<UserDM>
}

