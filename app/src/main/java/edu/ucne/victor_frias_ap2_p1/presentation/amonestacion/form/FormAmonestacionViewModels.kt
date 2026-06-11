package edu.ucne.victor_frias_ap2_p1.presentation.amonestacion.form

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.victor_frias_ap2_p1.domain.amonestacion.model.Amonestacion
import edu.ucne.victor_frias_ap2_p1.domain.amonestacion.useCase.DeleteAmonestacionUseCase
import edu.ucne.victor_frias_ap2_p1.domain.amonestacion.useCase.GetAmonestacionUseCase
import edu.ucne.victor_frias_ap2_p1.domain.amonestacion.useCase.UpsertAmonestacionUseCase
import edu.ucne.victor_frias_ap2_p1.presentation.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormAmonestacionViewModel @Inject constructor(
    private val getAmonestacionUseCase: GetAmonestacionUseCase,
    private val upsertAmonestacionUseCase: UpsertAmonestacionUseCase,
    private val deleteAmonestacionUseCase: DeleteAmonestacionUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val routerArgs = savedStateHandle.toRoute<Screen.AmonestacionForm>()
    private val amonestacionId: Int = routerArgs.amonestacionId

    private val _state = MutableStateFlow(FormAmonestacionUiState())
    val state: StateFlow<FormAmonestacionUiState> = _state.asStateFlow()

    init {
        loadAmonestacion(amonestacionId)
    }

    fun onEvent(event: FormAmonestacionUiEvent){
        when(event){
            is FormAmonestacionUiEvent.nombreChange -> _state.update {
                it.copy(nombre = event.values, nombreError = null)
            }
            is FormAmonestacionUiEvent.montoChange -> _state.update {
                it.copy(monto = event.values, montoError = null)
            }
            is FormAmonestacionUiEvent.razonChange -> _state.update {
                it.copy(razon = event.values, razonError = null)
            }
            FormAmonestacionUiEvent.Save -> onSave()
            FormAmonestacionUiEvent.Delete -> onDelete()
        }
    }

    private fun loadAmonestacion(id: Int?){
        if(id == null || id == 0){
            _state.update { it.copy(isNew = true, amonestacionId = null) }
            return
        }
        viewModelScope.launch {
            val amonestacion = getAmonestacionUseCase(id)
            if(amonestacion != null){
                _state.update {
                    it.copy(
                        isNew = false,
                        amonestacionId = amonestacion.amonetacionId,
                        nombre = amonestacion.nombre,
                        monto = amonestacion.monto.toString(),
                        razon = amonestacion.razon
                    )
                }
            } else {
                _state.update { it.copy(isNew = true, amonestacionId = null) }
            }
        }
    }

    private fun onSave(){
        if (_state.value.isSaving) return

        val nombreValido = state.value.nombre.isNotBlank()
        val montoValido = state.value.monto.toDoubleOrNull() != null
        val razonValida = state.value.razon.isNotBlank()

        if(!nombreValido || !montoValido || !razonValida){
            _state.update {
                it.copy(
                    nombreError = if (!nombreValido) "El nombre no puede estar vacío" else null,
                    montoError = if (!montoValido) "Ingrese un monto válido" else null,
                    razonError = if (!razonValida) "La razón no puede estar vacía" else null
                )
            }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isSaving = true) }
            val amonestacion = Amonestacion(
                amonetacionId = state.value.amonestacionId ?: 0,
                nombre = state.value.nombre.trim(),
                monto = state.value.monto.toDouble(),
                razon = state.value.razon.trim()
            )

            val result = upsertAmonestacionUseCase(amonestacion)
            result.onSuccess { newId ->
                _state.update {
                    it.copy(
                        isSaving = false,
                        saved = true,
                        amonestacionId = newId,
                        isNew = false
                    )
                }
            }.onFailure { error ->
                _state.update { it.copy(
                    isSaving = false,
                    nombreError = error.message
                ) }
            }
        }
    }

    private fun onDelete(){
        val id = state.value.amonestacionId ?: return
        viewModelScope.launch {
            _state.update { it.copy(isDeleting = true) }
            deleteAmonestacionUseCase(id)
            _state.update { it.copy(isDeleting = false, deleted = true) }
        }
    }
}