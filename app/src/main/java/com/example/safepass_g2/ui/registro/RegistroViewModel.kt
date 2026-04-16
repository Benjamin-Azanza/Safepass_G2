package com.example.safepass_g2.ui.registro

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.safepass_g2.data.Asistente
import com.example.safepass_g2.domain.RegistroResultado
import com.example.safepass_g2.domain.procesarAsistente

class RegistroViewModel : ViewModel() {

    var state = mutableStateOf<RegistroState>(RegistroState.Idle)
        private set

    fun registrar(nombre: String, edadTexto: String, tipoEntrada: String) {

        val asistente = Asistente(
            nombre = nombre,
            edad = null,
            tipoEntrada = tipoEntrada
        ).apply {
            // apply: valida formato del nombre antes de continuar el proceso
            if (this.nombre.isBlank()) {
                state.value = RegistroState.Error("El nombre es obligatorio")
                return
            }
        }

        val edad = edadTexto.toIntOrNull()

        edad?.let {
            val asistenteFinal = asistente.copy(edad = it)

            val resultado = procesarAsistente(asistenteFinal) { a ->
                a.tipoEntrada.uppercase() == "VIP"
            }

            state.value = when (resultado) {
                is RegistroResultado.Valido -> {
                    RegistroState.Success(resultado.resumen)
                }
                is RegistroResultado.Invalido -> {
                    RegistroState.Error(resultado.mensaje)
                }
            }

        } ?: run {
            state.value = RegistroState.Error("Edad inválida")
        }
    }
}