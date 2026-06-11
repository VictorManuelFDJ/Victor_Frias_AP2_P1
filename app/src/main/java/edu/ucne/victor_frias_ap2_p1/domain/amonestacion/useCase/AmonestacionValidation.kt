package edu.ucne.victor_frias_ap2_p1.domain.amonestacion.useCase

data class AmonestacionValidation(
    val isValid: Boolean,
    val error: String? = null
)
fun validateNombre(nombre: String, nombresExistentes: List<String> = emptyList()): AmonestacionValidation {
    return when {
        nombre.isBlank() -> AmonestacionValidation(
            false,
            "Este campo es obligatorio"
        )
        nombre.length < 3 -> AmonestacionValidation(
            false,
            "El nombre tiene que ser mayor de 3 caracteres"
        )
        else -> AmonestacionValidation(true)
    }
}

fun validateMonto(monto: String ): AmonestacionValidation{
    return when{
        monto.isBlank() -> AmonestacionValidation(false,
            "Este campo es obligatorio")
        monto.toDoubleOrNull() == null -> AmonestacionValidation(false,
            "Ingrese un sueldo valido")
        monto.toDouble() <= 0.0 -> AmonestacionValidation(false,
            "El balance no puede ser menor de 0")
        else -> AmonestacionValidation(true)
    }
}

fun validateRazon(razon: String): AmonestacionValidation{
    return when{
        razon.isBlank() -> AmonestacionValidation(false,
            "Este campo es obligatorio")
        razon.length <= 6 -> AmonestacionValidation(false,
            "La Razon tiene que ser mas de 6 carateres")
        else -> AmonestacionValidation(true)
    }
}

