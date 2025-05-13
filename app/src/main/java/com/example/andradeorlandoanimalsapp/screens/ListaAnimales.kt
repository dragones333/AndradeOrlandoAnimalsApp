package com.example.andradeorlandoanimalsapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.andradeorlandoanimalsapp.model.Animal
import com.example.andradeorlandoanimalsapp.network.ApiClient

@Composable
fun ListaAnimales(navController: NavHostController) {
    var animales by remember { mutableStateOf<List<Animal>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(true) {
        try {
            animales = ApiClient.animalService.getAnimals()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            isLoading = false
        }
    }

    if (isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn {
            items(animales) { animal ->
                AnimalItem(animal, navController)
            }
        }
    }
}

@Composable
fun AnimalItem(animal: Animal, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("detalleAnimal/${animal.id}")
            }
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(animal.image),
                contentDescription = animal.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Text(text = animal.name, style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(8.dp))
            Text(text = animal.description, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
        }
    }
}
