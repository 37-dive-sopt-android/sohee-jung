package com.sopt.dive.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.dive.core.network.RepositoryPool
import com.sopt.dive.data.UserPrefs

class SignInViewModelFactory(
    private val userPrefs: UserPrefs
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            return SignInViewModel(
                authRepository = RepositoryPool.authRepository,
                userPrefs = userPrefs
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
    }
}
