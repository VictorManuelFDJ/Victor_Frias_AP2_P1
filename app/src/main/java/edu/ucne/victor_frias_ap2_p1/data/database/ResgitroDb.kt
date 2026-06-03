package edu.ucne.victor_frias_ap2_p1.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.victor_frias_ap2_p1.data.borrame.local.BorrameDao
import edu.ucne.victor_frias_ap2_p1.data.borrame.local.BorrameEntity


@Database(
    entities = [BorrameEntity:: class],
    version = 1
)

abstract class ResgitroDb : RoomDatabase(){
    abstract fun BorrameDao(): BorrameDao
}