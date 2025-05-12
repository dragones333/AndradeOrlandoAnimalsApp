package com.example.andradeorlandoanimalsapp.services

data class Animal(
    val id: Int,
    val name: String,
    val description: String,
    val image_url: String,
    val gallery: List<String>,
    val facts: List<String>,
    val habitat_id: Int
)