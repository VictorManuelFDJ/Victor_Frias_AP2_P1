package edu.ucne.victor_frias_ap2_p1.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.victor_frias_ap2_p1.data.amonestacion.local.AmonestacionDao
import edu.ucne.victor_frias_ap2_p1.data.amonestacion.local.AmonestacionEntity


@Database(
    entities = [AmonestacionEntity:: class],
    version = 3
)

abstract class ResgitroDb : RoomDatabase(){
    abstract fun AmonestacionDao(): AmonestacionDao
}
