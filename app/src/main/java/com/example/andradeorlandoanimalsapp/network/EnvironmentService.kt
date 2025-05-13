package com.example.andradeorlandoanimalsapp.network

import com.example.andradeorlandoanimalsapp.model.Environment
import retrofit2.http.GET

interface EnvironmentService {
    @GET("environments")
    suspend fun getEnvironments(): List<Environment>
}
