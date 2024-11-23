package io.github.vkindl.notes.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import io.github.vkindl.notes.core.data.database.NotesDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual val platformModule = module {
    single<NotesDatabase> { createNotesDatabase() }
}

private fun createNotesDatabase(): NotesDatabase {
    val dbFile = getDocumentDirectory() + "/notes.db"
    return Room.databaseBuilder<NotesDatabase>(
        name = dbFile,
    ).setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}

@OptIn(ExperimentalForeignApi::class)
private fun getDocumentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null
    )
    return requireNotNull(documentDirectory?.path)
}
