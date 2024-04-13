package com.guru.demoottapp.ui.theme.state

import com.guru.domain_common.model.UserDM

data class UserListState(
    val users: List<UserDM>? = null,
    val isLoading: Boolean = false
)