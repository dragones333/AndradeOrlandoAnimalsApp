package com.example.andradeorlandoanimalsapp.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Nature
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun BottomNavBar(navController: NavController) {
    NavigationBar(containerColor = Color(0xFF2F3E3E)) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Animales") },
            label = { Text("Inicio") },
            selected = false,
            onClick = {
                navController.navigate("listaAnimales") {
                    popUpTo("listaAnimales") { inclusive = true }
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Nature, contentDescription = "Ambientes") },
            label = { Text("Ambientes") },
            selected = false,
            onClick = {
                navController.navigate("listaAmbientes") {
                    popUpTo("listaAmbientes") { inclusive = true }
                }
            }
        )
    }
}
