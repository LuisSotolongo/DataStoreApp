package com.example.datastoreapp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUserEmail(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("UserEmail")
        val USER_EMAIL = stringPreferencesKey("user_email")
    }

    //se puede poner el Flow<String?> como opcional
    // o usar el operador elvis para darle un dato por defecto, en este caso vacio ""
    val getEmail: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[USER_EMAIL] ?: " "
        }

    //datastore funciona con corutinas o funciones suspendidas
    suspend fun saveEmail(email: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_EMAIL] = email
        }
    }
}