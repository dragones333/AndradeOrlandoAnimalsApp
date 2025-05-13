package com.example.andradeorlandoanimalsapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.andradeorlandoanimalsapp.model.Animal
import com.example.andradeorlandoanimalsapp.network.ApiClient
import com.example.andradeorlandoanimalsapp.components.BottomNavBar
import kotlinx.coroutines.launch

@Composable
fun DetalleAnimal(animalId: String, navController: NavController) {
    var animal by remember { mutableStateOf<Animal?>(null) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(animalId) {
        scope.launch {
            try {
                val allAnimals = ApiClient.animalService.getAnimals()
                animal = allAnimals.find { it.id == animalId }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    animal?.let { a ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF2B3A2D)), // Fondo verde
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = a.name,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Image(
                painter = rememberAsyncImagePainter(a.image),
                contentDescription = a.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(240.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = a.description,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (a.facts.isNotEmpty()) {
                Text(
                    text = "Hechos Interesantes",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFFDEE094)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Column(
                    modifier = Modifier.padding(horizontal = 24.dp)
                ) {
                    a.facts.forEach { fact ->
                        Text(
                            text = "• $fact",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (a.imageGallery.isNotEmpty()) {
                Text(
                    text = "Galería de ${a.name}",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFFDEE094)
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    items(a.imageGallery.size) { index ->
                        Image(
                            painter = rememberAsyncImagePainter(a.imageGallery[index]),
                            contentDescription = "Imagen $index",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(RoundedCornerShape(12.dp))
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            BottomNavBar(navController = navController)
        }
    } ?: run {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}


