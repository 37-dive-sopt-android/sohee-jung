package com.sopt.dive.presentation.my

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.dive.core.network.RepositoryPool
import com.sopt.dive.data.UserPrefs

class MyViewModelFactory(
    private val userPrefs: UserPrefs
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            return MyViewModel(
                myRepository = RepositoryPool.myRepository,
                userPrefs = userPrefs
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
    }
}
