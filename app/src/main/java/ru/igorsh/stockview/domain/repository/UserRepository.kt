package ru.igorsh.stockview.domain.repository

import ru.igorsh.stockview.domain.model.User

interface UserRepository {

    fun logInUser(user: User)
    fun registerUser(user: User)
}