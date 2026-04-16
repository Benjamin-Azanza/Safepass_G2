package com.example.safepass_g2.ui.registro
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RegistroScreen(viewModel: RegistroViewModel) {

    val state by viewModel.state

    var nombre by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var tipoEntrada by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("SafePass 2026", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") }
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = edad,
                onValueChange = { edad = it },
                label = { Text("Edad") }
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = tipoEntrada,
                onValueChange = { tipoEntrada = it },
                label = { Text("Tipo de Entrada (VIP / General)") }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    viewModel.registrar(nombre, edad, tipoEntrada)
                }
            ) {
                Text("Registrar")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 🔥 Renderizado por estado (when exhaustivo)
            when (state) {
                is RegistroState.Idle -> {
                    Text("Ingrese los datos del asistente")
                }

                is RegistroState.Success -> {
                    Text(
                        text = (state as RegistroState.Success).resumen,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                is RegistroState.Error -> {
                    Text(
                        text = (state as RegistroState.Error).mensaje,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}