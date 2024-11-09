package io.github.vkindl.notes.di

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import io.github.vkindl.notes.core.data.database.NotesDatabase
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

actual val platformModule = module {
    single<NotesDatabase> { createNotesDatabase(get()) }
}

private fun createNotesDatabase(context: Context): NotesDatabase {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("notes.db")

    return Room.databaseBuilder(
        context,
        NotesDatabase::class.java,
        dbFile.absolutePath
    ).setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
