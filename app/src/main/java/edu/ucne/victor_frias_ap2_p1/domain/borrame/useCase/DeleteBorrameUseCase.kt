package edu.ucne.victor_frias_ap2_p1.domain.borrame.useCase

import edu.ucne.victor_frias_ap2_p1.data.borrame.local.BorrameDao
import edu.ucne.victor_frias_ap2_p1.data.borrame.local.BorrameEntity
import javax.inject.Inject

class DeleteBorrameUseCase @Inject constructor(
    private val borrameDao: BorrameDao
) {
    suspend operator fun invoke(id: Int) {
        borrameDao.delete(BorrameEntity(borrameId = id))
    }
}
