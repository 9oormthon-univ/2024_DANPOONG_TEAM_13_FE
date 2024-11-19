package com.daon.onjung.data.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DataStoreDataSourceImpl @Inject constructor(
    private val preferenceDataStore: DataStore<Preferences>
) : DataStoreDataSource {

    override fun getIntValue(type: String): Flow<Int> {
        return preferenceDataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    exception.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { prefs ->
                prefs[intPreferencesKey(type)] ?: 0
            }
    }

    override suspend fun setIntValue(type: String, value: Int) {
        preferenceDataStore.edit { settings ->
            settings[intPreferencesKey(type)] = value
        }
    }

    override fun getStringValue(type: String): Flow<String> {
        return preferenceDataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    exception.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { prefs ->
                prefs[stringPreferencesKey(type)] ?: ""
            }
    }

    override suspend fun setStringValue(type: String, value: String) {
        preferenceDataStore.edit { settings ->
            settings[stringPreferencesKey(type)] = value
        }
    }

    override fun getBooleanValue(type: String): Flow<Boolean> {
        return preferenceDataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    exception.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { prefs ->
                prefs[booleanPreferencesKey(type)] ?: false
            }
    }

    override suspend fun setBooleanValue(type: String, value: Boolean) {
        preferenceDataStore.edit { settings ->
            settings[booleanPreferencesKey(type)] = value
        }
    }

    override suspend fun deleteStringValue(type: String) {
        preferenceDataStore.edit { settings ->
            settings.remove(stringPreferencesKey(type))
        }
    }

    override suspend fun deleteBooleanValue(type: String) {
        preferenceDataStore.edit { settings ->
            settings.remove(booleanPreferencesKey(type))
        }
    }
}