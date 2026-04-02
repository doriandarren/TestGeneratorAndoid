package com.www.testgeneratorandroid.modules.auth.services

import com.www.testgeneratorandroid.modules.auth.models.LoginRequest
import com.www.testgeneratorandroid.modules.auth.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

}