package edu.ucne.victor_frias_ap2_p1.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.victor_frias_ap2_p1.domain.amonestacion.repository.AmonestacionRepository
import edu.ucne.victor_frias_ap2_p1.data.amonestacion.repository.AmonestacionRepositoryImpl // Ajusta el import a tu implementación
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAmonestacionRepository(
        impl: AmonestacionRepositoryImpl
    ): AmonestacionRepository
}