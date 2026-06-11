package edu.ucne.victor_frias_ap2_p1.presentation.amonestacion.form

sealed interface FormAmonestacionUiEvent {
    data class nombreChange(val values: String): FormAmonestacionUiEvent
    data class montoChange(val values: String): FormAmonestacionUiEvent
    data class razonChange(val values: String): FormAmonestacionUiEvent
    data object Save: FormAmonestacionUiEvent
    data object Delete: FormAmonestacionUiEvent
}