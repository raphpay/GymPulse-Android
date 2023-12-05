package fr.twinpaw.gympulse.model.dataProvider

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow

class AuthDataProvider : ViewModel() {

    private val _isLoggedIn = MutableStateFlow(false)
    var isLoggedIn by mutableStateOf(false)
    var currentUser: FirebaseUser? by mutableStateOf(null)

    fun checkUserStatus() {
        val authUser = FirebaseAuth.getInstance().currentUser
        viewModelScope.launch {
            currentUser = authUser
            isLoggedIn = authUser != null
        }
    }

    fun logout() {
        isLoggedIn = false
        currentUser = null
    }

}
