package io.github.vkindl.notes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.vkindl.notes.navigation.Destination.Detail
import io.github.vkindl.notes.navigation.Destination.Notes
import io.github.vkindl.notes.presentation.NoteDetailScreen
import io.github.vkindl.notes.presentation.NotesScreen

@Composable
fun NotesAppGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Notes) {
        composable<Notes> {
            NotesScreen()
        }
        composable<Detail> {
            NoteDetailScreen()
        }
    }
}
