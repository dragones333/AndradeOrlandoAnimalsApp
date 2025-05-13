package com.example.andradeorlandoanimalsapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.andradeorlandoanimalsapp.model.Environment
import com.example.andradeorlandoanimalsapp.network.ApiClient
import kotlinx.coroutines.launch

@Composable
fun DetalleEnvironment(envId: String, navController: NavController) {
    var environment by remember { mutableStateOf<Environment?>(null) }
    var animales by remember { mutableStateOf<List<com.example.andradeorlandoanimalsapp.model.Animal>>(emptyList()) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(envId) {
        scope.launch {
            try {
                val environments = ApiClient.environmentService.getEnvironments()
                environment = environments.find { it._id == envId }

                val allAnimals = ApiClient.animalService.getAnimals()
                animales = allAnimals.filter { it.environmentId == envId }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    environment?.let { env ->
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = env.name, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))

            Image(
                painter = rememberAsyncImagePainter(env.image),
                contentDescription = env.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = env.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Animales en este ambiente", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))

            animales.forEach { animal ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(animal.image),
                        contentDescription = animal.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(MaterialTheme.shapes.extraLarge)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = animal.name, style = MaterialTheme.typography.bodyLarge)
                }
            }
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

