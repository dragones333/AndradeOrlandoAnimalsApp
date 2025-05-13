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
import com.example.andradeorlandoanimalsapp.model.Environment
import com.example.andradeorlandoanimalsapp.network.ApiClient
import kotlinx.coroutines.launch

@Composable
fun DetalleEnvironment(envId: String, navController: NavController) {
    var environment by remember { mutableStateOf<Environment?>(null) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(envId) {
        scope.launch {
            try {
                val environments = ApiClient.environmentService.getEnvironments()
                environment = environments.find { it._id == envId }
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
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = env.description)
        }
    } ?: run {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}
