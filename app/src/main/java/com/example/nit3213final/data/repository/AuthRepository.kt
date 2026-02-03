package com.example.nit3213final.data.repository

import com.example.nit3213final.BuildConfig
import com.example.nit3213final.data.remote.ApiService
import com.example.nit3213final.data.remote.dto.LoginRequest
import com.example.nit3213final.util.Result
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: ApiService
) : IAuthRepository {

    override suspend fun login(username: String, password: String): Result<String> {
        return try {
            val response = api.login(BuildConfig.AUTH_PATH, LoginRequest(username, password))
            Result.Success(response.keypass)
        } catch (t: Throwable) {
            Result.Error("Login failed. Check Sydney endpoint and credentials.", t)
        }
    }
}
