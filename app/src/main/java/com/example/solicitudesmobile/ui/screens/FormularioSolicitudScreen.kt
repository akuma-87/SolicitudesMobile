package com.example.solicitudesmobile.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.solicitudesmobile.ui.theme.SolicitudesMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioSolicitudScreen() {
    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var prioridad by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Nueva Solicitud") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // 1. Título
            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Título de la solicitud") },
                modifier = Modifier.fillMaxWidth()
            )

            // 2. Descripción
            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción detallada") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3
            )

            // 3. Categoría
            OutlinedTextField(
                value = categoria,
                onValueChange = { categoria = it },
                label = { Text("Categoría (ej: Soporte, Redes)") },
                modifier = Modifier.fillMaxWidth()
            )

            // 4. Prioridad
            OutlinedTextField(
                value = prioridad,
                onValueChange = { prioridad = it },
                label = { Text("Prioridad (Baja, Media, Alta)") },
                modifier = Modifier.fillMaxWidth()
            )

            // 5. Ubicación
            OutlinedTextField(
                value = ubicacion,
                onValueChange = { ubicacion = it },
                label = { Text("Ubicación / Sala") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Botón Guardar
            Button(
                onClick = { /* Lógica de guardado futura */ },
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