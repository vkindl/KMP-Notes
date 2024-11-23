package io.github.vkindl.notes.di

import io.github.vkindl.notes.core.data.NoteRepositoryImpl
import io.github.vkindl.notes.core.data.database.NotesDatabase
import io.github.vkindl.notes.core.data.source.NoteLocalDataSource
import io.github.vkindl.notes.core.data.source.NoteLocalDataSourceImpl
import io.github.vkindl.notes.core.domain.NoteRepository
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
    single<NoteLocalDataSource> { NoteLocalDataSourceImpl(get()) }
    single<NoteRepository> { NoteRepositoryImpl(get()) }

    factory { ObserveNotesUseCase(get()) }
    factory { ObserveNoteUseCase(get()) }
    factory { SaveNoteUseCase(get()) }
    factory { DeleteNoteUseCase(get()) }

    viewModelOf(::NotesViewModel)
    viewModelOf(::NoteDetailViewModel)
}
