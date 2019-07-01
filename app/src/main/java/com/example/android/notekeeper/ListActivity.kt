package com.example.android.notekeeper

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.example.android.notekeeper.adp.NoteAdp
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.content_list.*

class ListActivity : AppCompatActivity(), NoteAdp.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { openNewActivity(null) }

        listOfNotes.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))
        }

        listOfNotes.adapter = NoteAdp(DataManager.notes, this)
    }

    override fun onItemClicked(adapterPosition: Int) {
        openNewActivity(adapterPosition)
    }

    private fun openNewActivity(adpPosition: Int?) {
        val intent = Intent(this, MainActivity::class.java)
        adpPosition?.run { intent.putExtra(NOTE_POSITION, adpPosition) }
        startActivity(intent)
    }
}
