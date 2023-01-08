package com.example.noteapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.NoteApplication
import com.example.noteapp.R
import com.example.noteapp.databinding.ActivityUpdateNoteBinding
import com.example.noteapp.di.DaggerAppComponent
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel
import javax.inject.Inject

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding:ActivityUpdateNoteBinding

    @Inject
    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val appComponent = DaggerAppComponent.builder()
//            .application(application)
//            .build()
        val appComponent = (application as NoteApplication).component

        appComponent.inject(this)

        val note=intent.getSerializableExtra("UPDATE_NOTE") as Note
        binding.edtNoteTitle.setText(note.title)
        binding.edtNoteDes.setText(note.description)

        binding.btnUpdate.setOnClickListener {
            note.title = binding.edtNoteTitle.text.toString()
            note.description = binding.edtNoteDes.text.toString()
            noteViewModel.updateNote(note)
            finish()
        }
    }
}