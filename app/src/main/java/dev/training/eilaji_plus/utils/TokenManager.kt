package dev.training.eilaji_plus.utils

object TokenManager {

    private var token: String? = null
    fun getToken(): String? = token
    fun saveToken(): Nothing = TODO()
    fun revokeToken(): Nothing = TODO()
    fun isTokenValid(): Boolean = true
    fun isTokenRevoked(): Boolean = false
    fun clearToken(): Nothing = TODO()

}