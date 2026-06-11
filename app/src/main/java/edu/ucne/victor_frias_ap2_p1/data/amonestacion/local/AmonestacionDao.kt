package edu.ucne.victor_frias_ap2_p1.data.amonestacion.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface AmonestacionDao {
    @Upsert
    suspend fun upsert(entity: AmonestacionEntity): Long

    @Delete
    suspend fun delete(entity: AmonestacionEntity)

    @Query("SELECT * FROM Amonestacion")
    fun observeAll(): Flow<List<AmonestacionEntity>>

    @Query("SELECT * FROM Amonestacion WHERE amonestacionId = :id")
    suspend fun getById(id: Int): AmonestacionEntity?

    @Query("Delete From Amonestacion where amonestacionId = :id")
    suspend fun deleteById(id: Int)

    @Query("Select exists(Select 1 from Amonestacion where amonestacionId = :id)")
    suspend fun exists(id: Int): Boolean


}
