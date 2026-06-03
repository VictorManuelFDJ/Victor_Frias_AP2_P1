package edu.ucne.victor_frias_ap2_p1.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen{

     @Serializable
     data object BorrameList: Screen(){

     }

    @Serializable
    data class BorrameForm(val borrameId: Int = 0): Screen()
}