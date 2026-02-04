package com.example.nit3213final.data.remote.dto

data class DashboardResponse(
    val entities: List<Map<String, Any>>,
    val entityTotal: Int
)
