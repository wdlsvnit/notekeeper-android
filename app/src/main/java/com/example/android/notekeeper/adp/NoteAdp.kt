package com.example.android.notekeeper.adp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.notekeeper.NoteInfo
import com.example.android.notekeeper.R
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdp(private val noteInfoList: List<NoteInfo>, private val context: Context, private val listener: Listener) :
        RecyclerView.Adapter<NoteAdp.ViewHolder>() {

    interface Listener {
        fun onItemClicked(adapterPosition: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layout = inflater.inflate(R.layout.item_note, parent, false)

        return ViewHolder(layout, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, positionItem: Int) {
        holder.bindView(noteInfoList[positionItem], context)
    }

    override fun getItemCount(): Int = noteInfoList.size


    class ViewHolder(private val view: View, listener: Listener) : RecyclerView.ViewHolder(view) {

        init {
            view.setOnClickListener { listener.onItemClicked(adapterPosition) }
        }

        fun bindView(noteInfo: NoteInfo, context: Context) {
            view.item_note_info.text = context.getText(R.string.note)
            view.item_note_course.text = noteInfo.course?.title
            view.item_note_title.text = noteInfo.title
            view.item_note_text.text = noteInfo.text
        }

    }

}