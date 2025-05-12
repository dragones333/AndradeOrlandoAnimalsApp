package com.example.andradeorlandoanimalsapp.services

import retrofit2.http.GET

interface AnimalService {
    @GET("animals")
    suspend fun getAnimals(): List<Animal>
    @GET("environments")
    suspend fun getEnvironments(): List<Environment>
}
