package fr.twinpaw.gympulse.model.services

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.*

class FirestoreService {
    companion object {
        val shared = FirestoreService()
    }

    private val database = FirebaseFirestore.getInstance()
    private val USERS_COLLECTION = "USERS_COLLECTION"

    private constructor()

    suspend fun createUser(email: String) {
        val id = UUID.randomUUID().toString()
        val data = hashMapOf("email" to email)
        try {
            database.collection(USERS_COLLECTION).document(id).set(data).await()
        } catch (error: Exception) {
            throw error
        }
    }

    suspend fun checkUserAvailability(email: String): Boolean {
        var isAvailable = false
        if (email.isEmpty()) return isAvailable

        try {
            val snapshot = database.collection(USERS_COLLECTION)
                .whereEqualTo("email", email)
                .get().await()
            isAvailable = snapshot.documents.isEmpty()
        } catch (error: Exception) {
            throw error
        }

        return isAvailable
    }
}
