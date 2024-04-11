package com.guru.demoottapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guru.domain_common.model.UserDM
import com.guru.domain_common.usecase.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {

    private val _users = MutableStateFlow<List<UserDM>?>(null)
    val users: StateFlow<List<UserDM>?> = _users

    init {
        // Immediately load data from the local database
        loadUsersFromDatabase()
    }

    private fun loadUsersFromDatabase() {
        viewModelScope.launch {
            userUseCases.getUsersUseCase(isRemote = false).collect { userList ->
                _users.value = userList
            }
        }
    }

    // Method to refresh users
    fun refreshUsers() {
        viewModelScope.launch {
            userUseCases.refreshUsersUseCase()
        }
    }
}
