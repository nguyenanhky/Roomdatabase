package com.example.noteapp.activities

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.Logger
import com.example.noteapp.NoteApplication
import com.example.noteapp.R
import com.example.noteapp.adapter.NoteAdapter
import com.example.noteapp.database.NoteDatabase
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.di.DaggerAppComponent
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    @Inject
    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val appComponent = DaggerAppComponent.builder()
//            .application(application)
//            .build()
        val appComponent =(application as NoteApplication).component
        appComponent.inject(this)

        initControls()
        initEvents()
        Logger.lod(message = "afdsf")


    }

    private fun initEvents() {
        binding.btnOpenAddActivity.setOnClickListener {
            Logger.lod(message = "MainActivity : ${NoteDatabase.getInstance(this)}")
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initControls() {
        val adapter= NoteAdapter(this@MainActivity, onItemClick, onItemDelete)
        
        binding.rvNote.setHasFixedSize(true)
        binding.rvNote.layoutManager = LinearLayoutManager(this)
        binding.rvNote.adapter = adapter

        noteViewModel.getAllNote().observe(this,{
            adapter.setNotes(it);
        })
    }

    private val onItemClick: (Note) -> Unit = {
        val intent = Intent(this, UpdateNoteActivity::class.java)
        intent.putExtra("UPDATE_NOTE", it)
        startActivity(intent)

    }
    private val onItemDelete: (Note) -> Unit = {
        noteViewModel.deleteNote(it);

    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.lod(message ="MainActivity : onDestroy" )

    }
}