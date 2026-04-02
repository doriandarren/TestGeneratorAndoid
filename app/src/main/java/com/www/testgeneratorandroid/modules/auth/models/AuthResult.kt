package com.www.testgeneratorandroid.modules.auth.models

data class AuthResult (
    val success: Boolean,
    val message: String,
    val token: String? = null
)