package com.example.andradeorlandoanimalsapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.example.andradeorlandoanimalsapp.components.BottomNavBar
import com.example.andradeorlandoanimalsapp.model.Animal
import com.example.andradeorlandoanimalsapp.model.Environment
import com.example.andradeorlandoanimalsapp.network.ApiClient
import kotlinx.coroutines.launch
@Composable
fun DetalleEnvironment(envId: String, navController: NavController) {
    var environment by remember { mutableStateOf<Environment?>(null) }
    var animales by remember { mutableStateOf<List<Animal>>(emptyList()) }
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2E3B2E))
    ) {
        environment?.let { env ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .padding(bottom = 90.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(env.image),
                    contentDescription = env.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = env.name,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = env.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(16.dp))

                animales.forEach { animal ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(animal.image),
                            contentDescription = animal.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = animal.name,
                            color = Color.White
                        )
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
}


