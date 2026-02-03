package com.example.nit3213final.data.remote

import com.example.nit3213final.data.remote.dto.DashboardResponse
import com.example.nit3213final.data.remote.dto.LoginRequest
import com.example.nit3213final.data.remote.dto.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiService {

    @POST
    suspend fun login(
        @Url url: String,
        @Body request: LoginRequest
    ): LoginResponse

    @GET("dashboard/{keypass}")
    suspend fun dashboard(@Path("keypass") keypass: String): DashboardResponse
}
