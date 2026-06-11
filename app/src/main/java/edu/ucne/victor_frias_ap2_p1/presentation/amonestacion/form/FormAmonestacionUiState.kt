package edu.ucne.victor_frias_ap2_p1.presentation.amonestacion.form

import java.time.LocalDate

data class FormAmonestacionUiState(
    val amonestacionId: Int? = null,
    val nombre: String =" ",
    val monto: String =" ",
    val razon: String =" ",


    val nombreError: String? = null,
    val montoError: String? = null,
    val razonError: String? = null,

    val isSaving: Boolean = false,
    val isDeleting: Boolean = false,
    val isNew: Boolean = true,
    val saved: Boolean = false,
    val deleted: Boolean = false
)