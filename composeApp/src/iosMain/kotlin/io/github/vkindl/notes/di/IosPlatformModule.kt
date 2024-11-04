package io.github.vkindl.notes.di

import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.vkindl.notes.data.NotesDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual val platformModule = module {
    single<NotesDatabase> { getNotesDatabaseBuilder().build() }
}

private fun getNotesDatabaseBuilder(): RoomDatabase.Builder<NotesDatabase> {
    val dbFile = getDocumentDirectory() + "/notes.db"
    return Room.databaseBuilder<NotesDatabase>(name = dbFile)
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
