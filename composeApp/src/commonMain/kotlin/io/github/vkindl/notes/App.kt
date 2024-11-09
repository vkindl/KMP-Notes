package io.github.vkindl.notes

import androidx.compose.runtime.Composable
import io.github.vkindl.notes.core.presentation.theme.NotesTheme
import io.github.vkindl.notes.navigation.NotesAppGraph
import org.koin.compose.KoinContext

@Composable
fun App() {
    KoinContext {
        NotesTheme {
            NotesAppGraph()
        }
    }
}
