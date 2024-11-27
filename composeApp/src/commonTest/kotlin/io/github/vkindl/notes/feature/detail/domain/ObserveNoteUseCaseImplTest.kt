package io.github.vkindl.notes.feature.detail.domain

import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.mock
import dev.mokkery.verify
import io.github.vkindl.notes.core.domain.Note
import io.github.vkindl.notes.core.domain.NoteRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ObserveNoteUseCaseImplTest {

    private lateinit var noteRepository: NoteRepository
    private lateinit var sut: ObserveNoteUseCaseImpl

    @BeforeTest
    fun setUp() {
        noteRepository = mock<NoteRepository>()

        sut = ObserveNoteUseCaseImpl(
            noteRepository = noteRepository
        )
    }

    @Test
    fun `should emit note by id on invoke`() = runTest {
        every { noteRepository.observeNoteById(ANY_ID) } returns flowOf(note)

        val result = sut.invoke(id = ANY_ID).first()

        verify { noteRepository.observeNoteById(id = ANY_ID) }
        assertEquals(note, result)
    }

    private companion object {
        const val ANY_ID = 1
        val note = Note(
            id = ANY_ID,
            title = "title",
            description = "description"
        )
    }
}
