package com.example.andradeorlandoanimalsapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.andradeorlandoanimalsapp.components.BottomNavBar
import com.example.andradeorlandoanimalsapp.model.Animal

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListaAnimales(navController: NavController) {
    val context = LocalContext.current
    var animales by remember { mutableStateOf(listOf<Animal>()) }

    LaunchedEffect(true) {
        animales = AnimalService1().getAnimales()
    }

    Scaffold(
        bottomBar = { BottomNavBar(navController = navController) },
        containerColor = Color(0xFF2F3E3E)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Animales", color = Color.White, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(24.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(animales) { animal ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("detalleAnimal/${animal.id}")
                            }
                    ) {
                        AsyncImage(
                            model = animal.image,
                            contentDescription = animal.name,
                            modifier = Modifier
                                .size(160.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            animal.name,
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                }
            }
        }
    }
}
