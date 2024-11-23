package io.github.vkindl.notes.core.data

import io.github.vkindl.notes.core.data.source.NoteLocalDataSource
import io.github.vkindl.notes.core.domain.NoteRepository
import io.github.vkindl.notes.core.domain.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl(
    private val noteLocalDataSource: NoteLocalDataSource
) : NoteRepository {

    override fun observeAllNotes(): Flow<List<Note>> {
        return noteLocalDataSource.observeAllNotes().map { notes ->
            notes.map { entity -> entity.toDomain() }
        }
    }

    override fun observeNoteById(id: Int): Flow<Note?> {
        return noteLocalDataSource.observeNoteById(id).map { entity ->
            entity?.toDomain()
        }
    }

    override suspend fun insertNote(note: Note) {
        return noteLocalDataSource.insertNote(note.toEntity())
    }

    override suspend fun deleteNoteById(id: Int) {
        noteLocalDataSource.deleteNoteById(id)
    }
}
