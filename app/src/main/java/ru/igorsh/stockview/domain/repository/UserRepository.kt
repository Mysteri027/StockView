package ru.igorsh.stockview.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import ru.igorsh.stockview.domain.model.User

interface UserRepository {

    fun logInUser(user: User): Task<AuthResult>
    fun registerUser(user: User): Task<AuthResult>
    fun logOutUser()
}