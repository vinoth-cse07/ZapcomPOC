package com.zcg.zapcompoc

import android.app.Application
import android.content.Context
import com.zcg.zapcompoc.di.ApplicationComponent
import com.zcg.zapcompoc.di.DaggerApplicationComponent
import com.zcg.zapcompoc.di.UIModule

class ItemsApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(UIModule(applicationContext))
        appContext = applicationContext

    }
}