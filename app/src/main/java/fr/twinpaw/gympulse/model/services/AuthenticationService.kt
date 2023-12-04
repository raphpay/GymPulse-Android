package fr.twinpaw.gympulse.model.services
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.tasks.await

class AuthenticationService private constructor() {
    companion object {
        val shared = AuthenticationService()
    }

    suspend fun signIn(email: String, password: String): FirebaseUser? {
        var user: FirebaseUser? = null

        try {
            val authResult = FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()
            user = authResult.user
        } catch (error: Exception) {
            println(error.localizedMessage)
            throw error
        }

        return user
    }

    suspend fun createUser(email: String, password: String): FirebaseUser? {
        var user: FirebaseUser? = null

        try {
            val authResult = FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).await()
            user = authResult.user
        } catch (error: Exception) {
            println(error.localizedMessage)
            throw error
        }

        return user
    }

    fun signOut(): Boolean {
        var isSignedOut = false
        try {
            FirebaseAuth.getInstance().signOut()
            isSignedOut = true
        } catch (error: Exception) {
            println(error.localizedMessage)
            throw error
        }
        return isSignedOut
    }
}
