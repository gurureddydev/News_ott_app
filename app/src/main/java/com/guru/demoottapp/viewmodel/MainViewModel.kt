package com.guru.demoottapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guru.data_common.local.User
import com.guru.data_common.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _users = MutableStateFlow<List<User>?>(null)
    val users: StateFlow<List<User>?> = _users

    init {
        // Immediately load data from the local database
        loadUsersFromDatabase()
    }

    private fun loadUsersFromDatabase() {
        viewModelScope.launch {
            userRepository.getUsers().collect { userList ->
                _users.value = userList
            }
        }
    }

    // Method to refresh users
    fun refreshUsers() {
        viewModelScope.launch {
            // Check if the local database contains data
            val localUsers = _users.value
            if (localUsers == null || localUsers.isEmpty()) {
                // If no data in local database, fetch from network
                userRepository.refreshUsers()
            } else {
                // If data is present in local database, update UI with existing data
                _users.value = localUsers
            }
        }
    }
}
