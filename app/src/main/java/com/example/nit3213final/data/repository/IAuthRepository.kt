package com.example.nit3213final.data.repository

import com.example.nit3213final.util.Result

interface IAuthRepository {
    suspend fun login(username: String, password: String): Result<String>
}