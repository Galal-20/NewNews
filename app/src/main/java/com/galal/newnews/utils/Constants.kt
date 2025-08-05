package com.galal.newnews.utils

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Constants {

    val api_key = listOf(
        "68cee2c9169c43a7af37d02b8291d4de",
        "e828836db50b4ceaa979843af64438d1",
        "593dae45fbf9439bb512271bb34b2495",
        "6309159f9cea443b8d0adba428d26836"

    )
    val url_api = "https://newsapi.org/"
}


// 593dae45fbf9439bb512271bb34b2495
// e828836db50b4ceaa979843af64438d1
// 68cee2c9169c43a7af37d02b8291d4de



object ApiKeyManager {

    private val Context.dataStore by preferencesDataStore("api_key_prefs")

    private val KEY_INDEX = intPreferencesKey("current_key_index")
    private val KEY_PREFIX = "request_count_"
    private const val MAX_REQUESTS = 100
    private const val TAG = "ApiKeyManager"

    fun getApiKey(context: Context): String {
        return runBlocking {
            val prefs = context.dataStore.data.first()
            val keyIndex = prefs[KEY_INDEX] ?: 0

            val requestKey = intPreferencesKey("$KEY_PREFIX$keyIndex")
            val requestCount = prefs[requestKey] ?: 0

            if (requestCount >= MAX_REQUESTS) {
                // Move to next key
                val nextIndex = (keyIndex + 1) % Constants.api_key.size
                context.dataStore.edit { settings ->
                    settings[KEY_INDEX] = nextIndex
                }

                Log.d(TAG, "🔄 Switching to API key #$nextIndex")
                Log.d(TAG, "✅ Using API key: ${Constants.api_key[nextIndex]}")

                Constants.api_key[nextIndex]
            } else {
                // Increment count
                context.dataStore.edit { settings ->
                    settings[requestKey] = requestCount + 1
                }

                Log.d(TAG, "➡️ Request with API key #$keyIndex: ${Constants.api_key[keyIndex]}")
                Log.d(TAG, "📊 Request count for key #$keyIndex: ${requestCount + 1}/$MAX_REQUESTS")

                Constants.api_key[keyIndex]
            }
        }
    }

   /* fun reset(context: Context) {
        runBlocking {
            context.dataStore.edit { settings ->
                settings[KEY_INDEX] = 0
                Constants.api_key.indices.forEach { i ->
                    settings.remove(intPreferencesKey("$KEY_PREFIX$i"))
                }
            }
            Log.d(TAG, "🔁 API key usage reset")
        }
    }*/
}