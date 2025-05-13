package com.example.andradeorlandoanimalsapp.components

import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun BottomNavBar(navController: NavController) {
    NavigationBar(containerColor = Color(0xFFDEE094)) {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("listaAnimales") },
            icon = { Icon(Icons.Default.Pets, contentDescription = "Animales") },
            label = { Text("Inicio") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("listaAmbientes") },
            icon = { Icon(Icons.Default.Nature, contentDescription = "Ambientes") },
            label = { Text("Ambientes") }
        )
    }
}
