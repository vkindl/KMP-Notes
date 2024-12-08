package io.github.vkindl.notes.core.data

import io.github.vkindl.notes.core.domain.SelectedNoteRepository

class SelectedNoteRepositoryImpl : SelectedNoteRepository {

    private var noteId: Int? = null

    override fun setNoteId(id: Int?) {
        noteId = id
    }

    override fun getNoteId(): Int? {
        return noteId
    }
}
