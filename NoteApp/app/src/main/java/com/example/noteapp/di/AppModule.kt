package com.example.noteapp.di

import android.app.Application
import com.example.noteapp.database.NoteDatabase
import com.example.noteapp.database.dao.NoteDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideNoteDatabase(application: Application):NoteDatabase = NoteDatabase.getInstance(application)

    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase):NoteDao = noteDatabase.getNoteDao()
}
