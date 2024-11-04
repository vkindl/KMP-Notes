package io.github.vkindl.notes.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import io.github.vkindl.notes.data.source.NotesLocalDataSource
import io.github.vkindl.notes.data.source.NotesLocalDataSourceImpl
import io.github.vkindl.notes.data.database.NotesDatabase
import io.github.vkindl.notes.data.NotesRepositoryImpl
import io.github.vkindl.notes.domain.usecase.AddNoteUseCase
import io.github.vkindl.notes.domain.usecase.DeleteNoteUseCase
import io.github.vkindl.notes.domain.NotesRepository
import io.github.vkindl.notes.domain.usecase.ObserveNotesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformModule: Module

val commonModule = module {
    single { getNotesDatabase(get()) }
    single { get<NotesDatabase>().notesDao() }
    single<NotesLocalDataSource> { NotesLocalDataSourceImpl(get()) }
    single<NotesRepository> { NotesRepositoryImpl(get()) }

    factory { ObserveNotesUseCase(get()) }
    factory { AddNoteUseCase(get()) }
    factory { DeleteNoteUseCase(get()) }
}

private fun getNotesDatabase(
    builder: RoomDatabase.Builder<NotesDatabase>
): NotesDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
