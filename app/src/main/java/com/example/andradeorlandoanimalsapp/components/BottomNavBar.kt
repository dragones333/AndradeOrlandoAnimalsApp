package com.example.andradeorlandoanimalsapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.andradeorlandoanimalsapp.screens.*

@Composable
fun BottomNavBar(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(56.dp)
                .align(Alignment.Center)
                .clip(RoundedCornerShape(50))
                .background(Color(0xFFDEE094)),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("listaAnimales") },
                    icon = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                Icons.Filled.Pets,
                                contentDescription = "Animales",
                                tint = Color.Black
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Inicio", color = Color.Black, fontSize = 16.sp)
                        }
                    },
                    label = null
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("listaAmbientes") },
                    icon = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                Icons.Filled.Landscape,
                                contentDescription = "Ambientes",
                                tint = Color.Black
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Ambientes", color = Color.Black, fontSize = 16.sp)
                        }
                    },
                    label = null
                )
            }
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
