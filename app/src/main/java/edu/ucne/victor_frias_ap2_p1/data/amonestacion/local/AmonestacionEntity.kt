package edu.ucne.victor_frias_ap2_p1.data.amonestacion.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Amonestacion")
data class AmonestacionEntity(
    @PrimaryKey(autoGenerate = true)

    val amonestacionId: Int = 0,
    val nombre: String = " ",
    val razon: String = " ",
    val monto: Double = 0.0

)