package ru.igorsh.stockview.data.local

interface UserStorage {
    fun get(): Boolean
    fun set(status: Boolean)
}