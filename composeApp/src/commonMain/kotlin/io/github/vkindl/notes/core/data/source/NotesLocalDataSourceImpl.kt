package io.github.vkindl.notes.core.data.source

import io.github.vkindl.notes.core.data.database.NotesDao
import io.github.vkindl.notes.core.data.model.NoteEntity
import kotlinx.coroutines.flow.Flow

class NotesLocalDataSourceImpl(
    private val notesDao: NotesDao
) : NotesLocalDataSource {

    override fun observeAllNotes(): Flow<List<NoteEntity>> {
        return notesDao.observeAllNotes()
    }

    override fun observeNoteById(id: Int): Flow<NoteEntity?> {
        return notesDao.observeNoteById(id)
    }

    override suspend fun insertNote(note: NoteEntity) {
        return notesDao.insertNote(note)
    }

    override suspend fun deleteNoteById(id: Int) {
        return notesDao.deleteNoteById(id)
    }
}
