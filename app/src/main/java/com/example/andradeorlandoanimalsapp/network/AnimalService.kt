package com.example.andradeorlandoanimalsapp.network

import com.example.andradeorlandoanimalsapp.model.Animal
import retrofit2.http.GET

interface AnimalService {
    @GET("animals")
    suspend fun getAnimals(): List<Animal>
}
