package io.github.vkindl.notes.core.data

import io.github.vkindl.notes.core.data.model.NoteEntity
import io.github.vkindl.notes.core.domain.Note

fun NoteEntity.toDomain(): Note {
    return Note(
        id = id,
        title = title,
        description = description
    )
}

fun Note.toEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        description = description
    )
}