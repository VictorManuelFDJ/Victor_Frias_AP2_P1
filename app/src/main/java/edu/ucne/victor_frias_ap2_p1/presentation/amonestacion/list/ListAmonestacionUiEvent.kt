package edu.ucne.victor_frias_ap2_p1.presentation.amonestacion.list

sealed class ListAmonestacionUiEvent {
    object Load: ListAmonestacionUiEvent()
    object Refresh: ListAmonestacionUiEvent()
    data class Delete(val id: Int): ListAmonestacionUiEvent()
    data class ShowMessage(val massage: String): ListAmonestacionUiEvent()
    object ClearMessage: ListAmonestacionUiEvent()
    object CreateNew: ListAmonestacionUiEvent()
    data class Edit(val id: Int): ListAmonestacionUiEvent()

}



