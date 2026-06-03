package edu.ucne.victor_frias_ap2_p1.domain.borrame.repository

import edu.ucne.victor_frias_ap2_p1.domain.borrame.model.Borrame
import kotlinx.coroutines.flow.Flow
 interface BorrameRepository {

    open fun observeBorrame(): Flow<List<Borrame>>

    open suspend fun getBorrame(id: Int): Borrame

    suspend fun upsert(borrame: Borrame): Int

    suspend fun delete(id: Int)

    suspend fun exists(id: Int): Boolean

}