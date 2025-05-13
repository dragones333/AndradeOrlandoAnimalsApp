package com.example.andradeorlandoanimalsapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.andradeorlandoanimalsapp.model.Animal
import com.example.andradeorlandoanimalsapp.network.ApiClient
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
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = a.name, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = rememberAsyncImagePainter(a.image),
                contentDescription = a.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = a.description)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Curiosidades:")
            a.facts.forEach { fact ->
                Text("- $fact", style = MaterialTheme.typography.bodyMedium)
            }
        }
    } ?: run {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}
