// Navegador.kt
package com.example.andradeorlandoanimalsapp.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*

sealed class Pantalla(val ruta: String, val icono: ImageVector, val titulo: String) {
    object Animales : Pantalla("animales", Icons.Default.Home, "Animales")
    object Ambientes : Pantalla("ambientes", Icons.Default.Info, "Ambientes")
}

@Composable
fun Navegador() {
    val navController = rememberNavController()
    val items = listOf(Pantalla.Animales, Pantalla.Ambientes)

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { pantalla ->
                    BottomNavigationItem(
                        icon = { Icon(pantalla.icono, contentDescription = pantalla.titulo) },
                        label = { Text(pantalla.titulo) },
                        selected = currentRoute == pantalla.ruta,
                        onClick = {
                            if (currentRoute != pantalla.ruta) {
                                navController.navigate(pantalla.ruta) {
                                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Pantalla.Animales.ruta,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Pantalla.Animales.ruta) {
                ListaAnimales(navController)
            }
            composable(Pantalla.Ambientes.ruta) {
                ListaAmbiente(navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VistaPreviaNavegador() {
    Navegador()
}
