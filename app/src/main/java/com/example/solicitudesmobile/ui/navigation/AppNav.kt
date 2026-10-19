package com.example.solicitudesmobile.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.solicitudesmobile.ui.SolicitudViewModel
import com.example.solicitudesmobile.ui.screens.FormularioSolicitudScreen
import com.example.solicitudesmobile.ui.screens.QrScannerScreen
import com.example.solicitudesmobile.ui.screens.SolicitudesScreen

sealed class Screen(val route: String) {
    object Lista : Screen("lista_solicitudes")
    object Formulario : Screen("formulario_solicitud")
    object Scanner : Screen("qr_scanner")
}

@Composable
fun AppNav(navController: NavHostController = rememberNavController()) {
    val solicitudViewModel: SolicitudViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Lista.route
    ) {
        composable(Screen.Lista.route) {
            SolicitudesScreen(
                onNavegarAFormulario = {
                    navController.navigate(Screen.Formulario.route)
                },
                solicitudViewModel = solicitudViewModel
            )
        }

        composable(Screen.Formulario.route) {
            FormularioSolicitudScreen(
                onVolverALista = {
                    navController.popBackStack()
                },
                onAbrirScanner = {
                    navController.navigate(Screen.Scanner.route)
                },
                solicitudViewModel = solicitudViewModel
            )
        }

        composable(Screen.Scanner.route) {
            QrScannerScreen(
                onCodigoEscaneado = { codigo ->
                    // 1. Registra el código y mensaje en el ViewModel
                    solicitudViewModel.registrarQRCaptado(codigo)
                    // 2. Redirecciona inmediatamente de vuelta al formulario
                    navController.popBackStack()
                },
                onVolver = {
                    navController.popBackStack()
                }
            )
        }
    }
}