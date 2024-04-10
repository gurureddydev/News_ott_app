package model

import kotlinx.coroutines.flow.Flow

data class UserDM(
    val id: Long? = 0,
    val name: String?,
    val image_url: String?
)
