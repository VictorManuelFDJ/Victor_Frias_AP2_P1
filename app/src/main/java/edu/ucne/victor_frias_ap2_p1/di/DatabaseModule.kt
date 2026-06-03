package edu.ucne.victor_frias_ap2_p1.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.victor_frias_ap2_p1.data.borrame.local.BorrameDao
import edu.ucne.victor_frias_ap2_p1.data.database.ResgitroDb
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent:: class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideRgistroDataBase(
        @ApplicationContext context: Context
    ): ResgitroDb{
        return Room.databaseBuilder(
            context,
            ResgitroDb::class.java,
            "Registro_DB"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideRgistroDataBase(database: ResgitroDb): BorrameDao{
        return database.BorrameDao()
    }


}