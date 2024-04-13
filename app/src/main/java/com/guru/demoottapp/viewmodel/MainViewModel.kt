package com.guru.demoottapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guru.demoottapp.ui.theme.state.UserListState
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

    private val _userListState = MutableStateFlow(UserListState())
    val userListState: StateFlow<UserListState> = _userListState

    init {
        // Immediately load data from the local database
        loadUsersFromDatabase()
    }

    private fun loadUsersFromDatabase() {
        viewModelScope.launch {
            _userListState.value = _userListState.value.copy(isLoading = true)
            userUseCases.getUsersUseCase(isRemote = false).collect { userList ->
                _userListState.value = UserListState(users = userList, isLoading = false)
            }
        }
    }

    fun refreshUsers() {
        viewModelScope.launch {
            _userListState.value = _userListState.value.copy(isLoading = true)
            userUseCases.refreshUsersUseCase()
            // No need to update state here, as it will be updated when loading completes
        }
    }
}
