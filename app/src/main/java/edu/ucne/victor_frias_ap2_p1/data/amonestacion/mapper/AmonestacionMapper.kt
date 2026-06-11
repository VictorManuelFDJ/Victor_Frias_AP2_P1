package edu.ucne.victor_frias_ap2_p1.data.amonestacion.mapper

import edu.ucne.victor_frias_ap2_p1.data.amonestacion.local.AmonestacionEntity
import edu.ucne.victor_frias_ap2_p1.domain.amonestacion.model.Amonestacion

fun AmonestacionEntity.toDomain(): Amonestacion = Amonestacion (
    amonetacionId = amonestacionId,
    nombre = nombre,
    razon = razon,
    monto = monto
)

fun Amonestacion.toEntity(): AmonestacionEntity = AmonestacionEntity(
    amonestacionId = amonetacionId,
    nombre = nombre,
    razon = razon,
    monto = monto
)