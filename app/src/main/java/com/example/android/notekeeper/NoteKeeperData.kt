package com.example.android.notekeeper

data class CourseInfo(val courseId: String, val title: String)

data class NoteInfo (var course: CourseInfo? = null, var title: String? = null, var text: String? = null)