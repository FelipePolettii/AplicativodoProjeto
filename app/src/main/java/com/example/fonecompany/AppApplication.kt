package com.example.fonecompany

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class AppApplication: Application() {
    init {
        _instance = this
    }
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
    companion object{
        private var _instance: AppApplication? = null
        val instance get() = _instance?.applicationContext ?: throw NullPointerException("Context is Null")
    }
}