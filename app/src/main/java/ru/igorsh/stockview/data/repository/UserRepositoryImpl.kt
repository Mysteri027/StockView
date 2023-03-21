package ru.igorsh.stockview.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import ru.igorsh.stockview.domain.model.User
import ru.igorsh.stockview.domain.repository.UserRepository

class UserRepositoryImpl(
    private val firebaseAuth: FirebaseAuth
) : UserRepository {

    override fun logInUser(user: User): Task<AuthResult> {
        return firebaseAuth.signInWithEmailAndPassword(user.email, user.password )
    }

    override fun registerUser(user: User): Task<AuthResult> {
        return firebaseAuth.createUserWithEmailAndPassword(user.email, user.password)
    }
}