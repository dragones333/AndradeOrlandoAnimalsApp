package com.example.andradeorlandoanimalsapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.andradeorlandoanimalsapp.screens.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

@Composable
fun BottomNavBar(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(56.dp)
            .clip(RoundedCornerShape(50))
            .background(Color(0xFFDEE094))
    ) {
        NavigationBar(
            containerColor = Color.Transparent,
            tonalElevation = 0.dp
        ) {
            // Icono de Animales
            NavigationBarItem(
                selected = false,
                onClick = { navController.navigate("listaAnimales") },
                icon = { Icon(Icons.Filled.Pets, contentDescription = "Animales", tint = Color.Black) }, // Icono de animales
                label = { Text("Inicio") }
            )
            // Icono de Ambientes
            NavigationBarItem(
                selected = false,
                onClick = { navController.navigate("listaAmbientes") },
                icon = { Icon(Icons.Filled.Landscape, contentDescription = "Ambientes", tint = Color.Black) }, // Icono de árbol
                label = { Text("Ambientes") }
            )
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "listaAnimales") {
        composable("listaAnimales") { ListaAnimales(navController) }
        composable("listaAmbientes") { ListaEnvironments(navController) }
        composable("detalleAnimal/{animalId}") { backStackEntry ->
            val animalId = backStackEntry.arguments?.getString("animalId") ?: ""
            DetalleAnimal(animalId, navController)
        }
        composable("detalleEnvironment/{envId}") { backStackEntry ->
            val envId = backStackEntry.arguments?.getString("envId") ?: ""
            DetalleEnvironment(envId, navController)
        }
    }
}
