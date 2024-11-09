package io.github.vkindl.notes.di

import io.github.vkindl.notes.core.data.NotesRepositoryImpl
import io.github.vkindl.notes.core.data.database.NotesDatabase
import io.github.vkindl.notes.core.data.source.NotesLocalDataSource
import io.github.vkindl.notes.core.data.source.NotesLocalDataSourceImpl
import io.github.vkindl.notes.core.domain.NotesRepository
import io.github.vkindl.notes.feature.notes.domain.DeleteNoteUseCase
import io.github.vkindl.notes.feature.detail.domain.ObserveNoteUseCase
import io.github.vkindl.notes.feature.notes.domain.ObserveNotesUseCase
import io.github.vkindl.notes.feature.detail.domain.SaveNoteUseCase
import io.github.vkindl.notes.feature.detail.presentation.NoteDetailViewModel
import io.github.vkindl.notes.feature.notes.presentation.NotesViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformModule: Module

val commonModule = module {
    single { get<NotesDatabase>().notesDao() }
    single<NotesLocalDataSource> { NotesLocalDataSourceImpl(get()) }
    single<NotesRepository> { NotesRepositoryImpl(get()) }

    factory { ObserveNotesUseCase(get()) }
    factory { ObserveNoteUseCase(get()) }
    factory { SaveNoteUseCase(get()) }
    factory { DeleteNoteUseCase(get()) }

    viewModelOf(::NotesViewModel)
    viewModelOf(::NoteDetailViewModel)
}
