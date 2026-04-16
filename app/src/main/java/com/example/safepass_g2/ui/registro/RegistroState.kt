package com.example.safepass_g2.ui.registro


sealed class RegistroState {

     //Estado inicial: el usuario aún no ha ingresado datos.

    object Idle : RegistroState()


     //Estado exitoso: los datos fueron validados correctamente.

    data class Success(
        val resumen: String
    ) : RegistroState()


     //Estado de error: datos inválidos o incompletos.
    data class Error(
        val mensaje: String
    ) : RegistroState()
}