package edu.ucne.victor_frias_ap2_p1.presentation.borrame.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.RestoreFromTrash
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.Scanner

@Composable
fun ListBorrameScreen(
 onAddBorrame: () -> Unit,
 onEditBorrame: (Int) -> Unit
){

    BorrameListBody(onAddBorrame = onAddBorrame, onEditBorrame = onEditBorrame)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BorrameListBody (
    onAddBorrame: () -> Unit,
    onEditBorrame: (Int) -> Unit
){
    Scaffold(
        topBar = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.RestoreFromTrash,
                    contentDescription = null,
                    modifier = Modifier.padding(start = 12.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text("Borrame")
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddBorrame,
                modifier = Modifier.testTag("fab_add")
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar Ocupacion"
                )
            }
        }

    ) {padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()
        ){
            Text(
                text = "No hay Registro",
                modifier = Modifier
                    .align(Alignment.Center)
                    .testTag("entity_Message"),
                style = MaterialTheme.typography.bodyLarge
            )
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){}
    }
}











































@Preview(showBackground = true, name = "List Screen Preview")
@Composable
fun ListScreenPreview(){
    ListBorrameScreen(
        onAddBorrame = TODO(),
        onEditBorrame = TODO()
    )
}
