package com.example.udemysecureauth.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.udemysecureauth.data.repository.DataStoreOperationsImpl
import com.example.udemysecureauth.data.repository.RepositoryImpl
import com.example.udemysecureauth.domain.repository.DataStoreOperations
import com.example.udemysecureauth.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    private const val PREFERENCES_NAME = "preferences"

    @Provides
    @Singleton
    fun provideDataStorePreferences(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile(PREFERENCES_NAME) }
        )
    }

    @Provides
    @Singleton
    fun provideDataStoreOperations(dataStore: DataStore<Preferences>): DataStoreOperations =
        DataStoreOperationsImpl(dataStore)

    @Provides
    @Singleton
    fun provideRepository(dataStoreOperations: DataStoreOperationsImpl):Repository =
        RepositoryImpl(dataStoreOperations)
}