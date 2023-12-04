package fr.twinpaw.gympulse.model.dataProvider

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow

class AuthDataProvider : ViewModel() {

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn get() = _isLoggedIn

    private val _currentUser = MutableStateFlow<FirebaseUser?>(null)
    val currentUser get() = _currentUser

    fun checkUserStatus() {
        val authUser = FirebaseAuth.getInstance().currentUser
        viewModelScope.launch {
            _currentUser.value = authUser
            _isLoggedIn.value = authUser != null
        }
    }
}
