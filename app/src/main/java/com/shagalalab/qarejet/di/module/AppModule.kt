package com.shagalalab.qarejet.di.module

import android.content.Context
import com.shagalalab.qarejet.QarejetApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: QarejetApp) {

    @Provides
    @Singleton
    fun providesApp() = app

    @Provides
    @Singleton
    fun providesContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun providesRouter(app: QarejetApp) = app.getRouter()

    @Provides
    @Singleton
    fun providesNavigationHolder(app: QarejetApp) = app.getNavigationHolder()
}