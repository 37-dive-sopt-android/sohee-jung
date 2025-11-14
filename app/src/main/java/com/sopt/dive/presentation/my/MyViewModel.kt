package com.sopt.dive.presentation.my

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.UserPrefs
import com.sopt.dive.data.my.repository.MyRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyViewModel(
    private val myRepository: MyRepository,
    private val userPrefs: UserPrefs
) : ViewModel() {
    private val _uiState = MutableStateFlow(MyState())
    val uiState: StateFlow<MyState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<MySideEffect>()
    val sideEffect: SharedFlow<MySideEffect> = _sideEffect.asSharedFlow()

    init {
        loadData()
    }

    private fun loadData(){
        viewModelScope.launch {
            val userId = userPrefs.getUserId() ?: return@launch
            val result = myRepository.getUser(userId)

            result.onSuccess { response ->
                val data = response.data

                _uiState.update {
                    it.copy(
                        id = data.id,
                        name = data.name,
                        email = data.email,
                        age = data.age
                    )
                }
            }
        }

    }

    fun onLogoutClicked(){
        viewModelScope.launch {
            _sideEffect.emit(MySideEffect.NavigateToSignIn)
        }
    }
}
