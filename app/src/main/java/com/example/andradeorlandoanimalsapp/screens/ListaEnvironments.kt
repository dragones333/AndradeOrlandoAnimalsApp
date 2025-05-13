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
import com.example.andradeorlandoanimalsapp.model.Environment
import com.example.andradeorlandoanimalsapp.network.ApiClient

@Composable
fun ListaEnvironments(navController: NavHostController) {
    var environments by remember { mutableStateOf<List<Environment>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(true) {
        try {
            environments = ApiClient.environmentService.getEnvironments()
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
            items(environments) { environment ->
                EnvironmentItem(environment, navController)
            }
        }
    }
}
@Composable
fun EnvironmentItem(environment: Environment, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("detalleEnvironment/${environment._id}")
            }
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(environment.image),
                contentDescription = environment.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Text(
                text = environment.name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = environment.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}
