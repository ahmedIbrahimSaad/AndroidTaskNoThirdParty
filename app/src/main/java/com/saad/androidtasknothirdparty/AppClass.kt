package com.saad.androidtasknothirdparty

import android.app.Application
import com.saad.androidtasknothirdparty.di.Injector

class AppClass: Application() {
    override fun onCreate() {
        super.onCreate()
        Injector.context=applicationContext
    }

}