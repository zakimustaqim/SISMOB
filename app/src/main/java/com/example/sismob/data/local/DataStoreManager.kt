package com.example.sismob.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DataStoreManager(context: Context) {

    private val dataStore = context.dataStore

    companion object {
        val NIK_KEY = stringPreferencesKey("nik")
        val NAME_KEY = stringPreferencesKey("name")
        val DEPT_KEY = stringPreferencesKey("dept")
        val TOKEN_KEY = stringPreferencesKey("token")
        val IS_LOGIN_KEY = booleanPreferencesKey("is_login")
    }

    suspend fun saveUser(nik: String, name: String, dept: String, token: String) {
        dataStore.edit { preferences ->
            preferences[NIK_KEY] = nik
            preferences[NAME_KEY] = name
            preferences[DEPT_KEY] = dept
            preferences[TOKEN_KEY] = token
            preferences[IS_LOGIN_KEY] = true
        }
    }

    suspend fun clearUser() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    val nik: Flow<String?> = dataStore.data.map { preferences ->
        preferences[NIK_KEY]
    }

    val name: Flow<String?> = dataStore.data.map { preferences ->
        preferences[NAME_KEY]
    }

    val dept: Flow<String?> = dataStore.data.map { preferences ->
        preferences[DEPT_KEY]
    }

    val token: Flow<String?> = dataStore.data.map { preferences ->
        preferences[TOKEN_KEY]
    }

    val isLogin: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[IS_LOGIN_KEY] ?: false
    }
}
