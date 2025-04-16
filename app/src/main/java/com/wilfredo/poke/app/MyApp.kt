package com.wilfredo.poke.app

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.ktx.Firebase

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()

        // Inicializar Firebase
        FirebaseApp.initializeApp(this)

        // (Opcional) Crashlytics
        FirebaseCrashlytics.getInstance().log("App iniciada")

        // (Opcional) Analytics
        //Firebase.analytics.logEvent("app_start", null)
    }
}