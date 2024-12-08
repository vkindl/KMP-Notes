package io.github.vkindl.notes.core.domain

interface SelectedNoteRepository {

    fun setNoteId(id: Int?)

    fun getNoteId(): Int?
}
