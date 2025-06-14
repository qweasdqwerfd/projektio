package com.example.qweasdqwerfd.api.token.token_storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

// Расширение контекста для создания DataStore
val Context.dataStore by preferencesDataStore(name = "auth")

// Реализация хранилища токенов
class TokenStorageImpl(private val context: Context) : TokenStorage {

    // Получение экземпляра DataStore
    private val dataStore = context.dataStore

    // Ключи для хранения токенов
    private companion object {
        val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
    }

    override suspend fun saveTokens(accessToken: String, refreshToken: String) {
        dataStore.edit { prefs ->
            prefs[ACCESS_TOKEN_KEY] = accessToken
            prefs[REFRESH_TOKEN_KEY] = refreshToken
        }
    }

    override suspend fun getAccessToken(): String? {
        val prefs = dataStore.data.first()
        return prefs[ACCESS_TOKEN_KEY]
    }

    override suspend fun getRefreshToken(): String? {
        val prefs = dataStore.data.first()
        return prefs[REFRESH_TOKEN_KEY]
    }
}
