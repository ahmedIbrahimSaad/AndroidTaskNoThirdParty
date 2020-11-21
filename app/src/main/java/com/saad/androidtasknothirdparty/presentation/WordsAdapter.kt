package com.saad.androidtasknothirdparty.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saad.androidtasknothirdparty.R

class WordsAdapter(private var occurrence: MutableMap<String, Int>) :
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
        private lateinit var tvWord: TextView
        private lateinit var tvOccurrence: TextView
        fun bind(word: String, occurrence: Int) {
            initViews(itemView)
            tvWord.text = word
            tvOccurrence.text = occurrence.toString()
        }

        private fun initViews(view: View) {
            tvWord = view.findViewById(R.id.tv_word)
            tvOccurrence = view.findViewById(R.id.tv_occurrence)
        }
    }

}


