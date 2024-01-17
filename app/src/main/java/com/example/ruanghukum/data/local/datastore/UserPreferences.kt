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
                preferences[PROFILE_ADDRESS] ?: "",
                preferences[PROFILE_PHONE_NUMBER] ?: "",
                preferences[PROFILE_GENDER] ?: "",
                preferences[PROFILE_JOB_TITLE] ?: "",
                preferences[PROFILE_ID_CARD_NUMBER] ?: "",
                preferences[PROFILE_BIRTH_DATE] ?: "",
                preferences[PROFILE_TOKEN] ?: "",
            )
        }
    }

    suspend fun saveSession(user: UserModel) {
        dataStore.edit { preferences ->
            preferences[PROFILE_EMAIL] = user.email
            preferences[PROFILE_NAME] = user.name
            preferences[PROFILE_PICTURE] = user.picture
            preferences[PROFILE_ADDRESS] = user.address
            preferences[PROFILE_PHONE_NUMBER] = user.phoneNumber
            preferences[PROFILE_GENDER] = user.gender
            preferences[PROFILE_JOB_TITLE] = user.jobTitle
            preferences[PROFILE_ID_CARD_NUMBER] = user.idCardNumber
            preferences[PROFILE_BIRTH_DATE] = user.birthDate
            preferences[PROFILE_TOKEN] = user.token
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
        private val PROFILE_ADDRESS = stringPreferencesKey("profile_address")
        private val PROFILE_PHONE_NUMBER = stringPreferencesKey("profile_phone_number")
        private val PROFILE_GENDER = stringPreferencesKey("profile_gender")
        private val PROFILE_JOB_TITLE = stringPreferencesKey("profile_job_title")
        private val PROFILE_ID_CARD_NUMBER = stringPreferencesKey("profile_id_card_number")
        private val PROFILE_BIRTH_DATE = stringPreferencesKey("profile_birth_date")
        private val PROFILE_TOKEN = stringPreferencesKey("profile_token")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

}