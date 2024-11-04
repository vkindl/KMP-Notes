package io.github.vkindl.notes

import android.app.Application
import io.github.vkindl.notes.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class NotesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(this@NotesApplication)
        }
    }
}
