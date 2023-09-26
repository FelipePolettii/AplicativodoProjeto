package com.example.fonecompany

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}