package com.harukadev.tabnews.database

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object SettingsDataStore {
    private val Context.dataStore by preferencesDataStore(name = "settings")

    private val THEME_KEY = stringPreferencesKey("theme")
    private val MAIN_COLORS_KEY = stringPreferencesKey("main_colors")

    suspend fun saveTheme(context: Context, theme: String) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = theme
        }
    }

    fun getTheme(context: Context): Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[THEME_KEY] ?: "system_default"
        }

    suspend fun saveMainColors(context: Context, pallet: Pair<Color, Color>) {
        context.dataStore.edit { preferences ->
            preferences[MAIN_COLORS_KEY] = pallet.toString()
        }
    }

    fun getMainColors(context: Context): Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[MAIN_COLORS_KEY] ?: "default"
        }
}