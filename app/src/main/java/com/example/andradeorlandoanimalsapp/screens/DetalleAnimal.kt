package com.example.andradeorlandoanimalsapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.andradeorlandoanimalsapp.components.BottomNavBar
import com.example.andradeorlandoanimalsapp.model.Animal
import com.example.andradeorlandoanimalsapp.network.AnimalService

@Composable
fun DetalleAnimal(navController: NavController, animalId: String) {
    var animal by remember { mutableStateOf<Animal?>(null) }

    LaunchedEffect(animalId) {
        animal = AnimalService().getAnimalById(animalId)
    }

    Scaffold(
        bottomBar = { BottomNavBar(navController = navController) },
        containerColor = Color(0xFF2F3E3E)
    ) {
        animal?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = it.image,
                    contentDescription = it.name,
                    modifier = Modifier
                        .size(250.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(it.name, color = Color.White, style = MaterialTheme.typography.headlineMedium)

                Spacer(modifier = Modifier.height(16.dp))
                Text(it.description, color = Color.White)

                Spacer(modifier = Modifier.height(16.dp))
                Text("Galería", color = Color.White, style = MaterialTheme.typography.titleLarge)
                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(it.gallery) { imgUrl ->
                        AsyncImage(
                            model = imgUrl,
                            contentDescription = "Imagen galería",
                            modifier = Modifier
                                .size(120.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text("Hechos Interesantes", color = Color.White, style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))
                it.facts.forEach { fact ->
                    Text("• $fact", color = Color.White)
                }
            }
        } ?: Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.White)
        }
    }
}
