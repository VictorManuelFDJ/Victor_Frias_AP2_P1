package edu.ucne.victor_frias_ap2_p1.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import edu.ucne.victor_frias_ap2_p1.presentation.borrame.form.FormBorrameScreen
import edu.ucne.victor_frias_ap2_p1.presentation.borrame.list.ListBorrameScreen

@Composable
fun navHost(
    navController: NavHostController = rememberNavController(),
    innerPadding: PaddingValues
){
    NavHost(
        navController = navController,
        startDestination = Screen.BorrameList,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable<Screen.BorrameList> {
            ListBorrameScreen(
                onAddBorrame = {
                    navController.navigate(Screen.BorrameForm())
                },
                onEditBorrame ={ id ->
                    navController.navigate(Screen.BorrameForm(borrameId = id))
                }
            )


        }
        composable<Screen.BorrameForm> {
            val args = it.toRoute<Screen.BorrameForm>()
            FormBorrameScreen(
                borrameId = args.borrameId,
                onBack = {
                    navController.navigateUp()
                }
            )
        }
    }

}