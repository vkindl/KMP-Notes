package io.github.vkindl.notes.navigation

import kotlinx.serialization.Serializable

sealed class Destination {

    @Serializable
    data object Notes : Destination()

    @Serializable
    data class Detail(val id: Int?) : Destination()
}
