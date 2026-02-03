package com.example.nit3213final.data.model

import java.io.Serializable

data class Entity(
    val id: String?,
    val fields: Map<String, String>,   // ✅ description removed here (dashboard list)
    val description: String?
) : Serializable