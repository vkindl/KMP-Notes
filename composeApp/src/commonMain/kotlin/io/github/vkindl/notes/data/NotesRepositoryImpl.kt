package io.github.vkindl.notes.data

import io.github.vkindl.notes.data.model.NoteEntity
import io.github.vkindl.notes.data.source.NotesLocalDataSource
import io.github.vkindl.notes.domain.model.Note
import io.github.vkindl.notes.domain.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotesRepositoryImpl(
    private val notesLocalDataSource: NotesLocalDataSource
) : NotesRepository {

    override fun observeAllNotes(): Flow<List<Note>> {
        return notesLocalDataSource.observeAllNotes().map { notes ->
            notes.map { entity ->
                Note(
                    id = entity.id,
                    title = entity.title
                )
            }
        }
    }

    override suspend fun insertNote(note: Note) {
        val entity = NoteEntity(
            id = note.id,
            title = note.title
        )

        return notesLocalDataSource.insertNote(entity)
    }

    override suspend fun deleteNoteById(id: Int) {
        notesLocalDataSource.deleteNoteById(id)
    }
}
