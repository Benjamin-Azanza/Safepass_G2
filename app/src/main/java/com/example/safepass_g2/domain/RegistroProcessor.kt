package com.example.safepass_g2.domain
import com.example.safepass_g2.data.Asistente


sealed class RegistroResultado {
    data class Valido(
        val resumen: String
    ) : RegistroResultado()

    data class Invalido(
        val mensaje: String
    ) : RegistroResultado()
}

fun procesarAsistente(
    asistente: Asistente,
    reglaExtra: (Asistente) -> Boolean
): RegistroResultado {
    if (!asistente.nombre.esNombreValido()) {
        return RegistroResultado.Invalido("El nombre no puede estar vacío.")
    }

    val edad = asistente.edad
        ?: return RegistroResultado.Invalido("La edad es obligatoria o inválida.")

    if (!edad.esMayorDeEdad()) {
        return RegistroResultado.Invalido("El asistente debe ser mayor de edad.")
    }

    val tipoEntradaNormalizado = asistente.tipoEntrada.normalizarTipoEntrada()

    if (tipoEntradaNormalizado.isEmpty()) {
        return RegistroResultado.Invalido("Debe seleccionar un tipo de entrada.")
    }

    val tienePrioridad = reglaExtra(asistente)

    val resumen = buildString {
        append("Registro exitoso: ")
        append("${asistente.nombre}, ")
        append("edad $edad, ")
        append("entrada $tipoEntradaNormalizado")

        if (tienePrioridad) {
            append(", acceso prioritario habilitado")
        } else {
            append(", acceso estándar")
        }
    }

    return RegistroResultado.Valido(resumen)
}