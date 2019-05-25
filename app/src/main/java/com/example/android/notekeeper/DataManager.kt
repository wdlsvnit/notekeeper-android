package com.example.android.notekeeper

object DataManager {
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        initializeCourses()
        initializeNotes()
    }

    private fun initializeCourses() {
        var c = CourseInfo("physics", "Physics")
        courses[c.courseId] = c

        c = CourseInfo("chemistry","Chemistry")

        courses[c.courseId] = c

        // Notice how the order is reversed
        c = CourseInfo("math","Mathematics")

        courses[c.courseId] = c
    }

    private fun initializeNotes() {
        notes.add(NoteInfo(courses["physics"]!!, "TEST1", "TEST1"))
        notes.add(NoteInfo(courses["chemistry"]!!, "TEST2", "TEST2"))
        notes.add(NoteInfo(courses["math"]!!, "TEST3", "TEST3"))
        notes.add(NoteInfo(courses["math"]!!, "TEST4", "TEST4"))
        notes.add(NoteInfo(courses["physics"]!!, "TEST5", "TEST5"))
        notes.add(NoteInfo(courses["chemistry"]!!, "TEST6", "TEST6"))
    }
}