package io.github.vkindl.notes.navigation

import kotlinx.serialization.Serializable

sealed class Destination {

    @Serializable
    data object Notes : Destination()

    @Serializable
    data object Detail : Destination()
}
