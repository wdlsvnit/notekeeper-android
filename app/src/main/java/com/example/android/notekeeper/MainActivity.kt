package com.example.android.notekeeper

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var notePosition = POSITION_NOT_SET

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val adapterCourses = ArrayAdapter<CourseInfo>(this, android.R.layout.simple_spinner_item, DataManager.courses.values.toList())
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        dropDownCourses.adapter = adapterCourses

        notePosition = savedInstanceState?.getInt(NOTE_POSITION, POSITION_NOT_SET) ?: intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET)

        if(notePosition != POSITION_NOT_SET) displayNote() else createNote()
    }

    private fun createNote() {
        DataManager.notes.add(NoteInfo())
        notePosition = DataManager.notes.lastIndex
    }

    private fun displayNote() {
        val note = DataManager.notes[notePosition];
        val coursePosition = DataManager.courses.values.indexOf(note.course)

        dropDownCourses.setSelection(coursePosition)

        textFieldTitle.setText(note.title)
        textFieldNoteContent.setText(note.text)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_previous -> {
                movePrevious()
                true
            }
            R.id.action_next -> {
                moveNext()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPause() {
        super.onPause()
        saveNote()
    }

    private fun saveNote() {
        val note = DataManager.notes[notePosition]
        note.title = textFieldTitle.text.toString()
        note.text = textFieldNoteContent.text.toString()
        note.course = dropDownCourses.selectedItem as CourseInfo
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if(notePosition == 0) {
            disableMenuItem(menu, R.id.action_previous)
        }

        if(notePosition >= DataManager.notes.lastIndex) {
            disableMenuItem(menu, R.id.action_next)
        }

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putInt(NOTE_POSITION, notePosition)
    }

    private fun disableMenuItem(menu: Menu?, menuItem: Int) {
        val item = menu?.findItem(menuItem)

        if(item != null) {
            item.icon = getDrawable(R.drawable.ic_no_white_24dp)
            item.isEnabled = false
        }
    }

    // Todo: would be cool if person could swipe right for next and left for previous right?
    private fun movePrevious() {
        --notePosition
        displayNote()
        invalidateOptionsMenu()
    }

    private fun moveNext() {
        ++notePosition
        displayNote()
        invalidateOptionsMenu()
    }
}
