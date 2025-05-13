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
import androidx.compose.ui.draw.clip
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
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
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
            .clickable {
                navController.navigate("detalleEnvironment/${environment._id}")
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(environment.image),
                contentDescription = environment.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(MaterialTheme.shapes.extraLarge)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = environment.name,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
