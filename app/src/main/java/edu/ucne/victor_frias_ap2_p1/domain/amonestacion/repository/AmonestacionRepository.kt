package edu.ucne.victor_frias_ap2_p1.domain.amonestacion.repository

import edu.ucne.victor_frias_ap2_p1.domain.amonestacion.model.Amonestacion
import kotlinx.coroutines.flow.Flow
 interface AmonestacionRepository {

    open fun observeAmonestacion(): Flow<List<Amonestacion>>

    open suspend fun getAmonestacion(id: Int): Amonestacion?

    suspend fun upsert(borrame: Amonestacion): Int

    suspend fun delete(id: Int)

    suspend fun exists(id: Int): Boolean

}