package com.example.solicitudesmobile.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.solicitudesmobile.ui.screens.FormularioSolicitudScreen
import com.example.solicitudesmobile.ui.screens.QrScannerScreen
import com.example.solicitudesmobile.ui.screens.SolicitudesScreen

// 1. Definición de las rutas de navegación
sealed class Screen(val route: String) {
    object Lista : Screen("lista_solicitudes")
    object Formulario : Screen("formulario_solicitud")
    object Scanner : Screen("qr_scanner") // <-- Se agrega la nueva ruta aquí
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
                },
                onAbrirScanner = { // Permite ir al escáner si agregas un botón en el formulario
                    navController.navigate(Screen.Scanner.route)
                }
            )
        }

        // 2. Se agrega el destino del Escáner QR aquí
        composable(Screen.Scanner.route) {
            QrScannerScreen(
                onCodigoEscaneado = { codigo ->
                    // Guarda el resultado del código QR para consumirlo en la pantalla anterior
                    navController.previousBackStackEntry?.savedStateHandle?.set("qr_result", codigo)
                    navController.popBackStack()
                },
                onVolver = {
                    navController.popBackStack()
                }
            )
        }
    }
}