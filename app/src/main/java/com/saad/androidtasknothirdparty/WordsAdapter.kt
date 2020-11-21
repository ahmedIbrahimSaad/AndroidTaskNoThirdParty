package com.saad.androidtasknothirdparty

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WordsAdapter(var occurrence: MutableMap<String, Int>) :
    RecyclerView.Adapter<WordsAdapter.WordsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {

        return WordsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.word_item, parent, false)
        )
    }

    fun setWordsList(occurrence: MutableMap<String, Int>) {
        this.occurrence = occurrence
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {
        val wordList = occurrence.keys
        val word = wordList.elementAt(position)
        val repeat = occurrence.getValue(word)
        holder.bind(word, repeat)
    }

    override fun getItemCount(): Int {
        return occurrence.count()
    }

    class WordsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var tv_word: TextView
        lateinit var tv_occurence: TextView
        fun bind(word: String, occurrence: Int) {
            initViews(itemView)
            tv_word.text = word
            tv_occurence.text = occurrence.toString()
        }

        fun initViews(view: View) {
            tv_word = view.findViewById(R.id.tv_word)
            tv_occurence = view.findViewById(R.id.tv_occurrence)
        }
    }

}


