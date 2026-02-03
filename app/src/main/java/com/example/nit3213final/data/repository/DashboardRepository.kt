package com.example.nit3213final.data.repository

import com.example.nit3213final.data.model.Entity
import com.example.nit3213final.data.remote.ApiService
import com.example.nit3213final.util.Result
import javax.inject.Inject

class DashboardRepository @Inject constructor(
    private val api: ApiService
) {
    suspend fun fetchEntities(keypass: String): Result<List<Entity>> {
        return try {
            val resp = api.dashboard(keypass)

            val entities = resp.entities.map { raw ->
                val id = raw["id"]?.toString()
                val description = raw["description"]?.toString()

                // ✅ remove description from list fields
                val fields = raw
                    .filterKeys { it != "description" }
                    .mapValues { (_, v) -> v.toString() }

                Entity(id = id, fields = fields, description = description)
            }

            Result.Success(entities)
        } catch (t: Throwable) {
            Result.Error("Failed to load dashboard.", t)
        }
    }
}
