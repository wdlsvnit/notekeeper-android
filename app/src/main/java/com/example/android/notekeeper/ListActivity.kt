package com.example.android.notekeeper

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.content_list.*

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }

        listOfNotes.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, DataManager.notes)

        listOfNotes.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, MainActivity::class.java)

            intent.putExtra(NOTE_POSITION, position)

            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        (listOfNotes.adapter as ArrayAdapter<NoteInfo>).notifyDataSetChanged()
    }
}
