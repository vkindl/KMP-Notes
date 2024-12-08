package io.github.vkindl.notes.feature.detail.presentation

import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.mock
import io.github.vkindl.notes.core.domain.Note
import io.github.vkindl.notes.core.domain.SelectedNoteRepository
import io.github.vkindl.notes.feature.detail.domain.ObserveNoteUseCase
import io.github.vkindl.notes.feature.detail.domain.SaveNoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class NoteDetailViewModelTest {

    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private lateinit var observeNoteUseCase: ObserveNoteUseCase
    private lateinit var saveNoteUseCase: SaveNoteUseCase
    private lateinit var selectedNoteRepository: SelectedNoteRepository
    private lateinit var sut: NoteDetailViewModel

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        selectedNoteRepository = mock {
            every { getNoteId() } returns ANY_ID
        }
        observeNoteUseCase = mock {
            every { invoke(ANY_ID) } returns flowOf(note)
        }
        saveNoteUseCase = mock()

        sut = NoteDetailViewModel(
            observeNoteUseCase = observeNoteUseCase,
            saveNoteUseCase = saveNoteUseCase,
            selectedNoteRepository = selectedNoteRepository
        )
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should observe note on init`() = runTest {
        assertEquals(
            expected = NoteDetailViewModel.NoteDetailUiState(
                id = ANY_ID,
                title = ANY_TITLE,
                description = ANY_DESCRIPTION
            ),
            actual = sut.state.value
        )
    }

    @Test
    fun `should update title on title change`() = runTest {
        sut.onTitleChange(UPDATED_TITLE)

        assertEquals(UPDATED_TITLE, sut.state.value.title)
    }

    @Test
    fun `should update description on description change`() = runTest {
        sut.onDescriptionChange(UPDATED_DESCRIPTION)

        assertEquals(UPDATED_DESCRIPTION, sut.state.value.description)
    }

    private companion object {
        const val ANY_ID = 0
        const val ANY_TITLE = "any title"
        const val ANY_DESCRIPTION = "any description"
        const val UPDATED_TITLE = "updated title"
        const val UPDATED_DESCRIPTION = "updated description"

        val note = Note(
            id = ANY_ID,
            title = ANY_TITLE,
            description = ANY_DESCRIPTION
        )
    }
}
