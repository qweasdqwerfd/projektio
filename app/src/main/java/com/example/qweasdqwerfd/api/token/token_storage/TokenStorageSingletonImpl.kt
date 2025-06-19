package com.example.qweasdqwerfd.api.token.token_storage

import android.annotation.SuppressLint
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.dataStore by preferencesDataStore(name = "auth")

@SuppressLint("StaticFieldLeak")
object TokenStorageSingletonImpl : TokenStorage {

    private lateinit var context: Context

    fun init(context: Context) {
        this.context = context.applicationContext
    }

    private val dataStore by lazy { context.dataStore }

    private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
    private val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")

    override suspend fun saveTokens(accessToken: String, refreshToken: String) {
        dataStore.edit {
            it[ACCESS_TOKEN_KEY] = accessToken
            it[REFRESH_TOKEN_KEY] = refreshToken
        }
    }

    override suspend fun getAccessToken(): String? {
        return dataStore.data.first()[ACCESS_TOKEN_KEY]
    }

    override suspend fun getRefreshToken(): String? {
        return dataStore.data.first()[REFRESH_TOKEN_KEY]
    }

    override suspend fun clearTokens() {
        dataStore.edit {
            it.remove(ACCESS_TOKEN_KEY)
            it.remove(REFRESH_TOKEN_KEY)
        }
    }
}
