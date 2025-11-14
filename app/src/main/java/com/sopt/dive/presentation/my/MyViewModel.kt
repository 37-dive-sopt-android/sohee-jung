package com.sopt.dive.presentation.my

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MyState())
    val uiState: StateFlow<MyState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<MySideEffect>()
    val sideEffect: SharedFlow<MySideEffect> = _sideEffect.asSharedFlow()

    init {

    }

    fun onLogoutClicked(){
        viewModelScope.launch {
            _sideEffect.emit(MySideEffect.NavigateToSignIn)
        }
    }
}
