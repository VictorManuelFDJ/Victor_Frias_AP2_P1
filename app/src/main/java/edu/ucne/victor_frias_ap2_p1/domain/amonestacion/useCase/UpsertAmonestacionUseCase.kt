package edu.ucne.victor_frias_ap2_p1.domain.amonestacion.useCase

import edu.ucne.victor_frias_ap2_p1.domain.amonestacion.model.Amonestacion
import edu.ucne.victor_frias_ap2_p1.domain.amonestacion.repository.AmonestacionRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UpsertAmonestacionUseCase @Inject constructor(
    private val repository: AmonestacionRepository
){
    suspend operator fun invoke(amonestacion: Amonestacion): Result<Int>{
        val listaActual = repository.observeAmonestacion().first()
            .filter{it.amonetacionId != amonestacion.amonetacionId}
            .map { it.nombre }

        val nombreResult = validateNombre(amonestacion.nombre, listaActual)
        if (!nombreResult.isValid){
            return Result.failure(IllegalArgumentException(nombreResult.error))
        }

        val montoResult = validateMonto(amonestacion.monto.toString())
        if (!montoResult.isValid){
            return Result.failure(IllegalArgumentException(montoResult.error))
        }
        return runCatching { repository.upsert(amonestacion) }

        val razonResult = validateRazon(amonestacion.razon)
        if(!razonResult.isValid){
            return Result.failure(IllegalArgumentException(razonResult.error))
        }
    }
}