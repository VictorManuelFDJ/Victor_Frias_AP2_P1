package edu.ucne.victor_frias_ap2_p1.data.amonestacion.repository

import edu.ucne.victor_frias_ap2_p1.data.amonestacion.local.AmonestacionDao
import edu.ucne.victor_frias_ap2_p1.data.amonestacion.mapper.toDomain
import edu.ucne.victor_frias_ap2_p1.data.amonestacion.mapper.toEntity
import edu.ucne.victor_frias_ap2_p1.domain.amonestacion.model.Amonestacion
import edu.ucne.victor_frias_ap2_p1.domain.amonestacion.repository.AmonestacionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AmonestacionRepositoryImpl @Inject constructor(
    private val localDataSource: AmonestacionDao
): AmonestacionRepository{
    override fun observeAmonestacion(): Flow<List<Amonestacion>> {
        return localDataSource.observeAll().map {entities
            -> entities.map {it.toDomain() } }
    }

    override suspend fun getAmonestacion(id: Int): Amonestacion?{
        return localDataSource.getById(id)?.toDomain()
    }

    override suspend fun delete(id: Int){
        return localDataSource.deleteById(id)
    }

    override suspend fun exists(id: Int): Boolean{
        return localDataSource.exists(id)
    }

    override suspend fun upsert(amonestacion: Amonestacion): Int{
        return localDataSource.upsert(amonestacion.toEntity()).toInt()
    }

}