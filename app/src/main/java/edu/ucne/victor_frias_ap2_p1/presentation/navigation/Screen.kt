package edu.ucne.victor_frias_ap2_p1.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen{

     @Serializable
     data object AmonestacionList: Screen(){

     }

    @Serializable
    data class AmonestacionForm(val amonestacionId: Int = 0): Screen()
}