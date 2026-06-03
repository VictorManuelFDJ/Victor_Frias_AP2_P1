package edu.ucne.victor_frias_ap2_p1.data.borrame.repository

import edu.ucne.victor_frias_ap2_p1.data.borrame.local.BorrameDao
import edu.ucne.victor_frias_ap2_p1.domain.borrame.model.Borrame
import edu.ucne.victor_frias_ap2_p1.domain.borrame.repository.BorrameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BorrameRepositoryImpl @Inject constructor(
    private val localDataSource: BorrameDao
): BorrameRepository{
    override fun observeBorrame(): Flow<List<Borrame>> {
        return TODO("Provide the return value")
    }

    override suspend fun getBorrame(id: Int): Borrame{
        return TODO("Provide the return value")
    }

    override suspend fun delete(id: Int){}

    override suspend fun exists(id: Int): Boolean{
        return TODO("Provide the return value")
    }

    override suspend fun upsert(borrame: Borrame): Int{
        return TODO("Provide the return value")
    }

}