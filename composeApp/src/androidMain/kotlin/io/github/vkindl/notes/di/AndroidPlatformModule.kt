package io.github.vkindl.notes.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.vkindl.notes.data.database.NotesDatabase
import org.koin.dsl.module

actual val platformModule = module {
    single<NotesDatabase> { getNotesDatabaseBuilder(get()).build() }
}

private fun getNotesDatabaseBuilder(context: Context): RoomDatabase.Builder<NotesDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("notes.db")

    return Room.databaseBuilder(
        context,
        NotesDatabase::class.java,
        dbFile.absolutePath
    )
}
