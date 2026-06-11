package edu.ucne.victor_frias_ap2_p1.presentation.amonestacion.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.victor_frias_ap2_p1.domain.amonestacion.useCase.DeleteAmonestacionUseCase
import edu.ucne.victor_frias_ap2_p1.domain.amonestacion.useCase.ObserveAmonestacionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListAmonestacionViewModels @Inject constructor(
    private val ObserveAmonestacionUseCase: ObserveAmonestacionUseCase,
    private val DeleteAmonestacionUseCase: DeleteAmonestacionUseCase
) : ViewModel(){
    private val _state = MutableStateFlow(ListAmonestacionUiState(isLoading = true))
    val state: StateFlow<ListAmonestacionUiState> = _state.asStateFlow()

    init {
        loadBanco()
    }

    fun loadBanco(){
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            ObserveAmonestacionUseCase().collectLatest { list ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        banco = list,
                        message = null
                    )
                }
            }
        }
    }

    private fun OnDelete(id: Int){
        viewModelScope.launch { DeleteAmonestacionUseCase(id)
            onEvent(ListAmonestacionUiEvent.ShowMessage("Eliminado"))
        }
    }

    fun onEvent(event: ListAmonestacionUiEvent) {
        when(event){
            ListAmonestacionUiEvent.Load -> loadBanco()
            ListAmonestacionUiEvent.Refresh -> loadBanco()
            is ListAmonestacionUiEvent.Delete -> OnDelete(event.id)
            is ListAmonestacionUiEvent.ShowMessage -> _state.update { it.copy(message = event.massage) }
            ListAmonestacionUiEvent.ClearMessage -> _state.update { it.copy(message = null)}
            ListAmonestacionUiEvent.CreateNew -> _state.update { it.copy(navigationToCreate = true) }
            is ListAmonestacionUiEvent.Edit -> _state.update { it.copy(navigationToEdit = event.id) }
        }
    }
}