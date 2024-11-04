package io.github.vkindl.notes.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import io.github.vkindl.notes.data.NotesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformModule: Module

val commonModule = module {
    single { getNotesDatabase(get()) }
    single { get<NotesDatabase>().notesDao() }
}

private fun getNotesDatabase(
    builder: RoomDatabase.Builder<NotesDatabase>
): NotesDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
