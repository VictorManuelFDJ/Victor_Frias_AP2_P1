package edu.ucne.victor_frias_ap2_p1.data.borrame.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Borrame")
data class BorrameEntity(
    @PrimaryKey(autoGenerate = true)

    val borrameId: Int = 0
)