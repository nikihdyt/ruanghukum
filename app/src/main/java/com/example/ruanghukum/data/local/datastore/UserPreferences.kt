package com.example.ruanghukum.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")
class UserPreferences private constructor(private val dataStore: DataStore<Preferences>) {

    fun getSession(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            UserModel(
                preferences[PROFILE_EMAIL] ?: "",
                preferences[PROFILE_NAME] ?: "",
                preferences[PROFILE_PICTURE] ?: "",
            )
        }
    }

    suspend fun saveSession(user: UserModel) {
        dataStore.edit { preferences ->
            preferences[PROFILE_EMAIL] = user.email
            preferences[PROFILE_NAME] = user.name
            preferences[PROFILE_PICTURE] = user.picture
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreferences? = null

        private val PROFILE_NAME = stringPreferencesKey("profile_name")
        private val PROFILE_EMAIL = stringPreferencesKey("profile_email")
        private val PROFILE_PICTURE = stringPreferencesKey("profile_picture")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

}