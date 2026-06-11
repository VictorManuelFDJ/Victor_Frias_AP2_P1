package edu.ucne.victor_frias_ap2_p1.presentation.amonestacion.list

import edu.ucne.victor_frias_ap2_p1.domain.amonestacion.model.Amonestacion


data class ListAmonestacionUiState(

    val isLoading: Boolean = false,
    val banco: List<Amonestacion> = emptyList(),
    val message: String? = null,
    val navigationToCreate: Boolean = false,
    val navigationToEdit: Int? = null,
    val error: String? = null
)