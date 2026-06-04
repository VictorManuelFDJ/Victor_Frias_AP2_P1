package edu.ucne.victor_frias_ap2_p1.data.borrame.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface BorrameDao {
    @Upsert
    suspend fun upsert(entity: BorrameEntity): Long

    @Delete
    suspend fun delete(entity: BorrameEntity)

    @Query("SELECT * FROM Borrame WHERE borrameId = :id")
    suspend fun find(id: Int): BorrameEntity?

    @Query("SELECT * FROM Borrame")
    fun getAll(): Flow<List<BorrameEntity>>
}
