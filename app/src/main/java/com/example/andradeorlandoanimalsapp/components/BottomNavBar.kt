package com.example.andradeorlandoanimalsapp.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place

@Composable
fun BottomNavBar(navController: NavController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    BottomNavigation(
        backgroundColor = Color(0xFF2D3B39), // Verde oscuro
        contentColor = Color.White
    ) {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") },
            selected = currentRoute == "listaAnimales",
            onClick = { navController.navigate("listaAnimales") }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Place, contentDescription = "Ambientes") },
                    label = { Text("Ambientes") },
            selected = currentRoute == "listaAmbientes",
            onClick = { navController.navigate("listaAmbientes") }
        )
    }
}
