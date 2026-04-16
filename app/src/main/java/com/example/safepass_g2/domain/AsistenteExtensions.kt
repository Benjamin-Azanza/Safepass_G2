package com.example.safepass_g2.domain

fun String.esNombreValido(): Boolean = this.trim().isNotEmpty()
fun Int.esMayorDeEdad(): Boolean = this >= 18
fun String.normalizarTipoEntrada(): String = this.trim().uppercase()