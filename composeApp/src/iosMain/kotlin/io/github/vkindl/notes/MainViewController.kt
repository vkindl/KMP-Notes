package io.github.vkindl.notes

import androidx.compose.ui.window.ComposeUIViewController
import io.github.vkindl.notes.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) { App() }