package com.example.andradeorlandoanimalsapp.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.andradeorlandoanimalsapp.screens.*

@Composable
fun BottomNavBar(navController: NavController) {
    NavigationBar(containerColor = Color(0xFFDEE094)) {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("listaAnimales") },
            icon = { Icon(Icons.Default.Home, contentDescription = "Animales") } ,
            label = { Text("Inicio") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("listaAmbientes") },
            icon = { Icon(Icons.Default.LocationOn, contentDescription = "Ambientes") },
            label = { Text("Ambientes") }
        )
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
