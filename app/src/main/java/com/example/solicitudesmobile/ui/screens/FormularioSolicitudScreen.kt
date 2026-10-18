package com.example.solicitudesmobile.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.solicitudesmobile.ui.SolicitudViewModel
import com.example.solicitudesmobile.ui.theme.SolicitudesMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioSolicitudScreen(
    solicitudViewModel: SolicitudViewModel = viewModel(),
    onVolverALista: () -> Unit = {},
    onAbrirScanner: () -> Unit = {}
) {
    val uiState by solicitudViewModel.uiState.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nueva Solicitud") },
                navigationIcon = {
                    IconButton(onClick = { onVolverALista() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver atrás"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Mensaje de éxito animado
            AnimatedVisibility(
                visible = uiState.mensajeExito != null,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                uiState.mensajeExito?.let { mensaje ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFD4EDDA)),
                        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                    ) {
                        Text(
                            text = mensaje,
                            color = Color(0xFF155724),
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }

            // 1. Título con validación
            OutlinedTextField(
                value = uiState.titulo,
                onValueChange = { solicitudViewModel.onTituloChanged(it) },
                label = { Text("Título de la solicitud") },
                isError = uiState.tituloError != null,
                supportingText = {
                    uiState.tituloError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                },
                trailingIcon = {
                    if (uiState.tituloError != null) {
                        Icon(Icons.Default.Warning, contentDescription = "Error", tint = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            // 2. Descripción con validación
            OutlinedTextField(
                value = uiState.descripcion,
                onValueChange = { solicitudViewModel.onDescripcionChanged(it) },
                label = { Text("Descripción detallada") },
                isError = uiState.descripcionError != null,
                supportingText = {
                    uiState.descripcionError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                },
                trailingIcon = {
                    if (uiState.descripcionError != null) {
                        Icon(Icons.Default.Warning, contentDescription = "Error", tint = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3
            )

            // 3. Categoría con validación
            OutlinedTextField(
                value = uiState.categoria,
                onValueChange = { solicitudViewModel.onCategoriaChanged(it) },
                label = { Text("Categoría (ej: Soporte, Redes)") },
                isError = uiState.categoriaError != null,
                supportingText = {
                    uiState.categoriaError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                },
                modifier = Modifier.fillMaxWidth()
            )

            // 4. Prioridad
            OutlinedTextField(
                value = uiState.prioridad,
                onValueChange = { solicitudViewModel.onPrioridadChanged(it) },
                label = { Text("Prioridad (Baja, Media, Alta)") },
                modifier = Modifier.fillMaxWidth()
            )

            // 5. Ubicación con validación
            OutlinedTextField(
                value = uiState.ubicacion,
                onValueChange = { solicitudViewModel.onUbicacionChanged(it) },
                label = { Text("Ubicación / Sala") },
                isError = uiState.ubicacionError != null,
                supportingText = {
                    uiState.ubicacionError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { onAbrirScanner() }, // O la lambda de navegación correspondiente
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Escanear Código QR")
            }

            // Botón Guardar con estado reactivo
            Button(
                onClick = { solicitudViewModel.guardarSolicitud() },
                enabled = uiState.esValido,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar Solicitud")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FormularioPreview() {
    SolicitudesMobileTheme {
        FormularioSolicitudScreen()
    }
}