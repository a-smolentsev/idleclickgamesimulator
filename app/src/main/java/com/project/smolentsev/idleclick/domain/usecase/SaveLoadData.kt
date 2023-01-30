package com.project.smolentsev.idleclick.domain.usecase

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.smolentsev.idleclick.domain.entity.ResCharacter

class SaveLoadData(context: Context) {
    private val gson = Gson()
    private val prefs: SharedPreferences = context.getSharedPreferences("MAIN_STORAGE", MODE_PRIVATE)

    fun saveItems(items: MutableList<ResCharacter>) {
        val json = gson.toJson(items)
        prefs.edit().putString("items", json).apply()
    }

    fun getItems(): MutableList<ResCharacter>? {
        val type = object : TypeToken<MutableList<ResCharacter>>() {}.type
        val json = prefs.getString("items", "")

        return gson.fromJson(json, type)
    }
}
