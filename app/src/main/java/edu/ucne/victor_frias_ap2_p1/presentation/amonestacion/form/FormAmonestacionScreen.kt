package edu.ucne.victor_frias_ap2_p1.presentation.amonestacion.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormAmonestacionScreen(
    amonestacionId: Int,
    viewModel: FormAmonestacionViewModel = hiltViewModel(),
    onBack: () -> Unit
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.saved, state.deleted) {
        if(state.saved || state.deleted){
            onBack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    if(state.amonestacionId == null || state.amonestacionId == 0) "Nuevo Registro" else "Editar Registro"
                ) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Atras"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = state.nombre,
                onValueChange = { viewModel.onEvent(FormAmonestacionUiEvent.nombreChange(it)) },
                label = { Text("Nombre") },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("input_nombre"),
                isError = state.nombreError != null,
                supportingText = state.nombreError?.let { { Text(it) } },
                singleLine = true
            )

            OutlinedTextField(
                value = state.monto,
                onValueChange = { viewModel.onEvent(FormAmonestacionUiEvent.montoChange(it)) },
                label = { Text("Monto") },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("input_monto"),
                isError = state.montoError != null,
                supportingText = state.montoError?.let { { Text(it) } },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )

            OutlinedTextField(
                value = state.razon,
                onValueChange = { viewModel.onEvent(FormAmonestacionUiEvent.razonChange(it)) },
                label = { Text("Razón") },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("input_razon"),
                isError = state.razonError != null,
                supportingText = state.razonError?.let { { Text(it) } },
                singleLine = false,
                minLines = 2,
                maxLines = 4
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        viewModel.onEvent(FormAmonestacionUiEvent.Delete)
                    },
                    modifier = Modifier.weight(1f),
                    enabled = !state.isNew && !state.isDeleting
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar"
                    )
                }

                Button(
                    onClick = {
                        viewModel.onEvent(FormAmonestacionUiEvent.Save)
                    },
                    modifier = Modifier.weight(1f),
                    enabled = !state.isSaving
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Guardar"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Guardar")
                }
            }
        }
    }
}