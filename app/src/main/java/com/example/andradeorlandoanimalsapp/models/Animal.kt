package com.example.andradeorlandoanimalsapp.models

data class Animal(
    val id: String,
    val name: String,
    val description: String,
    val image: String,
    val imageGallery: List<String>,
    val facts: List<String>,
    val environmentId: String
)
