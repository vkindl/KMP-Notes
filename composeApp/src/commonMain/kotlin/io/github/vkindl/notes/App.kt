package io.github.vkindl.notes

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import io.github.vkindl.notes.navigation.NotesAppGraph

@Composable
fun App() {
    MaterialTheme {
        NotesAppGraph()
    }
}
