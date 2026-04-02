package com.www.testgeneratorandroid.modules.auth.repositories

import com.www.testgeneratorandroid.core.network.RetrofitClient
import com.www.testgeneratorandroid.modules.auth.models.AuthResult
import com.www.testgeneratorandroid.modules.auth.models.LoginRequest
import com.www.testgeneratorandroid.modules.auth.services.AuthApiService

class AuthRepository {

    private val api: AuthApiService =
        RetrofitClient.retrofit.create(AuthApiService::class.java)

    suspend fun login(email: String, password: String): AuthResult {
        return try {
            val response = api.login(
                LoginRequest(
                    email = email,
                    password = password
                )
            )

            if (response.isSuccessful) {
                val body = response.body()

                AuthResult(
                    success = true,
                    message = body?.message ?: "Login correcto",
                    token = body?.token
                )
            } else {
                AuthResult(
                    success = false,
                    message = "Error HTTP: ${response.code()}",
                    token = null
                )
            }
        } catch (e: Exception) {
            AuthResult(
                success = false,
                message = "Error de conexión: ${e.message ?: "Error desconocido"}",
                token = null
            )
        }
    }
}