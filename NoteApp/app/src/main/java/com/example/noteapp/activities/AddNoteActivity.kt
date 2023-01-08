package com.example.noteapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.Logger
import com.example.noteapp.NoteApplication
import com.example.noteapp.R
import com.example.noteapp.database.NoteDatabase
import com.example.noteapp.databinding.ActivityAddNoteBinding
import com.example.noteapp.di.DaggerAppComponent
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel
import javax.inject.Inject

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding

    @Inject
    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val appComponent = DaggerAppComponent.builder()
//            .application(application)
//            .build()

        val appComponent = (application as NoteApplication).component
        appComponent.inject(this)

        binding.btnAdd.setOnClickListener {
            Logger.lod(message = "AddNoteActivity: ${NoteDatabase.getInstance(this)}")
            val note =
                Note(binding.edtNoteTitle.text.toString(), binding.edtNoteDes.text.toString())
            noteViewModel.insertNote(note)
            finish()
        }

    }
}