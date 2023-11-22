package com.example.fonecompany.repository.token

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.fonecompany.model.RefreshTokenResDTO
import com.example.fonecompany.model.TokenDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "preference")

class TokenDataStore {
    suspend fun savetoken(context: Context, tokenDTO: TokenDTO) {
        context.datastore.edit { settings ->
            val tokenKey = stringPreferencesKey("token")
            val isAdmin = booleanPreferencesKey("isAdmin")
            val refreshToken = stringPreferencesKey("refreshToken")
            settings[tokenKey] = tokenDTO.token
            settings[isAdmin] = tokenDTO.isAdmin
            settings[refreshToken] = tokenDTO.refreshToken
        }
    }
    suspend fun savenewtoken(context: Context, refreshTokenResDTO: RefreshTokenResDTO){
        context.datastore.edit { settings ->
            val tokenKey = stringPreferencesKey("token")
            settings[tokenKey] = refreshTokenResDTO.token
        }
    }

    fun gettoken(context: Context): Flow<String?> {
        val tokenKey = stringPreferencesKey("token")
        return context.datastore.data.map { preferences ->
            preferences[tokenKey]
        }
    }

    fun getisadmin(context: Context): Flow<Boolean?> {
        val isAdmin = booleanPreferencesKey("isAdmin")
        return context.datastore.data.map { preferences ->
            preferences[isAdmin]
        }
    }

    fun getrefreshtoken(context: Context): Flow<String?> {
        val refreshToken = stringPreferencesKey("refreshToken")
        return context.datastore.data.map { preference ->
            preference[refreshToken]
        }
    }
    suspend fun logout(context: Context){
        context.datastore.edit { settings ->
            val tokenKey = stringPreferencesKey("token")
            val isAdmin = booleanPreferencesKey("isAdmin")
            val refreshToken = stringPreferencesKey("refreshToken")
            settings.remove(tokenKey)
            settings.remove(isAdmin)
            settings.remove(refreshToken)
        }
    }
}