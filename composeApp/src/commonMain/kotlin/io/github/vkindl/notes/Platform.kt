package io.github.vkindl.notes

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform