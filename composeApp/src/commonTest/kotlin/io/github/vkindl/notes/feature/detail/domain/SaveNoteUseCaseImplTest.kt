package io.github.vkindl.notes.feature.detail.domain

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import io.github.vkindl.notes.core.domain.Note
import io.github.vkindl.notes.core.domain.NoteRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class SaveNoteUseCaseImplTest {

    private lateinit var noteRepository: NoteRepository
    private lateinit var sut: SaveNoteUseCaseImpl

    @BeforeTest
    fun setUp() {
        noteRepository = mock<NoteRepository>()

        sut = SaveNoteUseCaseImpl(
            noteRepository = noteRepository
        )
    }

    @Test
    fun `should insert note on invoke`() = runTest {
        everySuspend { noteRepository.insertNote(note) } returns Unit

        sut.invoke(note)

        verifySuspend { noteRepository.insertNote(note) }
    }

    private companion object {
        val note = Note(
            id = 1,
            title = "title",
            description = "description"
        )
    }
}
