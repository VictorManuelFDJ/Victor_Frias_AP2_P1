package edu.ucne.victor_frias_ap2_p1.presentation.amonestacion.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.victor_frias_ap2_p1.domain.amonestacion.model.Amonestacion

@Composable
fun ListAmonestacionScreen(
    viewModel: ListAmonestacionViewModels = hiltViewModel(), // Nombre exacto de tu ViewModel
    onAddAmonestacion: () -> Unit,
    onEditAmonestacion: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    AmonestacionListBody(state, viewModel::onEvent, onAddAmonestacion, onEditAmonestacion)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmonestacionListBody(
    state: ListAmonestacionUiState,
    onEvent: (ListAmonestacionUiEvent) -> Unit,
    onAddAmonestacion: () -> Unit,
    onEditAmonestacion: (Int) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.message) {
        state.message?.let { message ->
            snackbarHostState.showSnackbar(message)
            onEvent(ListAmonestacionUiEvent.ClearMessage)
        }
    }

    Scaffold(
        topBar = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.padding(start = 12.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text("Amonestaciones")
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddAmonestacion,
                modifier = Modifier.testTag("fab_add")
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .testTag("loading")
                )
            } else {
                if (state.banco.isEmpty()) {
                    Text(
                        text = "No hay amonestaciones registradas",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .testTag("entity_Message"),
                        style = MaterialTheme.typography.bodyLarge
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(
                            items = state.banco, // Cambiado de state.amonetacion a state.banco
                            key = { it.amonetacionId }
                        ) { amonestacion ->
                            AmonestacionItems(
                                amonestacion = amonestacion,
                                onEdit = { onEditAmonestacion(amonestacion.amonetacionId) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmonestacionItems(
    amonestacion: Amonestacion,
    onEdit: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxSize()
            .testTag("amonestacion_item_${amonestacion.amonetacionId}"),
        onClick = onEdit
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = amonestacion.nombre,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Monto: RD$ ${amonestacion.monto}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Razón: ${amonestacion.razon}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "List Screen Preview")
@Composable
fun ListScreenPreview() {
    AmonestacionListBody(
        state = ListAmonestacionUiState(),
        onEvent = {},
        onAddAmonestacion = {},
        onEditAmonestacion = {}
    )
}