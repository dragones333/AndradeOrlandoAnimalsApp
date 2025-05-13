package com.example.andradeorlandoanimalsapp.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.andradeorlandoanimalsapp.components.BottomNavBar
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF2B3A2F))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 96.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Animales",
                        style = MaterialTheme.typography.headlineSmall.copy(fontSize = 26.sp),
                        color = Color.White
                    )
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDEE094))
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Agregar", tint = Color.Black)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Agregar", color = Color.Black)
                    }
                }

                Text(
                    text = "Conoce a los animales más increíbles del mundo",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 12.dp),
                    color = Color.White
                )

                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(animales) { animal ->
                        AnimalItem(animal, navController)
                    }
                }
            }

        }
    }
}

@Composable
fun AnimalItem(animal: Animal, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("detalleAnimal/${animal.id}")
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(animal.image),
            contentDescription = animal.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = animal.name,
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 20.sp),
            color = Color.White
        )
    }
}




