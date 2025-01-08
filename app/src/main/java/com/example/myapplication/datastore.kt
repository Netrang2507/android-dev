package com.example.myapplication

import androidx.datastore.core.DataStore
import com.example.myapplication.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

suspend fun setUserPreferences(dataStore: DataStore<UserPreferences>, name: String, age: Int, isPremium: Boolean) {
    val preferences = UserPreferences.newBuilder()
        .setName(name)
        .setAge(age)
        .setIsPremium(isPremium)
        .build()

    // Update the data in the DataStore
    dataStore.updateData { preferences }
}
fun getUserPreferences(dataStore: DataStore<UserPreferences>): Flow<UserPreferences> {
    return dataStore.data
        .map { preferences ->
            preferences // This will emit the UserPreferences data to the UI
        }
}