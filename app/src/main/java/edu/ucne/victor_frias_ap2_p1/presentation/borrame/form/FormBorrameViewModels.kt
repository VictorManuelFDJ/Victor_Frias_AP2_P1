package edu.ucne.victor_frias_ap2_p1.presentation.borrame.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.victor_frias_ap2_p1.domain.borrame.useCase.DeleteBorrameUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormBorrameViewModel @Inject constructor(
    private val deleteBorrameUseCase: DeleteBorrameUseCase
) : ViewModel() {

    var borrameId by mutableStateOf(0)
    var nombre by mutableStateOf("")
    var nombreError by mutableStateOf<String?>(null)

    fun onNombreChange(newValue: String) {
        nombre = newValue
        nombreError = if (newValue.isBlank()) "El nombre no puede estar vacío" else null
    }

    fun delete() {
        viewModelScope.launch {
            deleteBorrameUseCase(borrameId)
        }
    }
}
