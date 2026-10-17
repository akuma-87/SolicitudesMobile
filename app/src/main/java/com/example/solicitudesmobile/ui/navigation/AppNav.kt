package com.example.solicitudesmobile.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.solicitudesmobile.ui.screens.FormularioSolicitudScreen
import com.example.solicitudesmobile.ui.screens.SolicitudesScreen

// Rutas de navegación de la app
sealed class Screen(val route: String) {
    object Lista : Screen("lista_solicitudes")
    object Formulario : Screen("formulario_solicitud")
}

@Composable
fun AppNav(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Lista.route
    ) {
        composable(Screen.Lista.route) {
            SolicitudesScreen(
                onNavegarAFormulario = {
                    navController.navigate(Screen.Formulario.route)
                }
            )
        }
        composable(Screen.Formulario.route) {
            FormularioSolicitudScreen(
                onVolverALista = {
                    navController.popBackStack()
                }
            )
        }
    }
}