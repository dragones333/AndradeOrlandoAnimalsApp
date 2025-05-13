package com.example.andradeorlandoanimalsapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.andradeorlandoanimalsapp.components.BottomNavBar
import com.example.andradeorlandoanimalsapp.model.Ambiente
import com.example.andradeorlandoanimalsapp.model.Animal
import com.example.andradeorlandoanimalsapp.network.AmbienteService
import com.example.andradeorlandoanimalsapp.network.AnimalService

@Composable
fun DetalleAmbiente(navController: NavController, ambienteId: String) {
    var ambiente by remember { mutableStateOf<Ambiente?>(null) }
    var animalesFiltrados by remember { mutableStateOf<List<Animal>>(emptyList()) }

    LaunchedEffect(ambienteId) {
        ambiente = AmbienteService().getAmbienteById(ambienteId)
        val todosAnimales = AnimalService().getAnimales()
        animalesFiltrados = todosAnimales.filter { it.ambienteId == ambienteId }
    }

    Scaffold(
        bottomBar = { BottomNavBar(navController = navController) },
        containerColor = Color(0xFF2F3E3E)
    ) {
        ambiente?.let {
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

                Spacer(modifier = Modifier.height(24.dp))
                Text("Animales del ambiente", color = Color.White, style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(animalesFiltrados) { animal ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
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
                                    .size(80.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(animal.name, color = Color.White)
                        }
                    }
                }
            }
        } ?: Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color.White)
        }
    }
}
